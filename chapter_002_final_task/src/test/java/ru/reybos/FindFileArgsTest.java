package ru.reybos;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FindFileArgsTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalid() {
        FindFileArgs params = new FindFileArgs(new String[]{"-d", "qwe"});
    }

    @Test
    public void whenValid() {
        FindFileArgs params = new FindFileArgs(new String[]{
                "-d", "root", "-n", "name", "-m", "-o", "out"
        });
        assertThat(params.rootDir(), is("root"));
        assertThat(params.searchedFileName(), is("name"));
        assertThat(params.outFile(), is("out"));
    }

    @Test
    public void whenSearchByFullName() throws IOException {
        FindFileArgs params = new FindFileArgs(new String[]{
                "-d", "root", "-n", "name.txt", "-f", "-o", "out"
        });
        File success = folder.newFile("name.txt");
        File fail = folder.newFile("aname.txt");
        Predicate<Path> func = params.searchFunc();
        assertTrue(func.test(success.toPath()));
        assertFalse(func.test(fail.toPath()));
    }

    @Test
    public void whenSearchByMask() throws IOException {
        FindFileArgs params = new FindFileArgs(new String[]{
                "-d", "root", "-n", "*.txt", "-m", "-o", "out"
        });
        File success = folder.newFile("name.txt");
        File fail = folder.newFile("name.doc");
        Predicate<Path> func = params.searchFunc();
        assertTrue(func.test(success.toPath()));
        assertFalse(func.test(fail.toPath()));
    }

    @Test
    public void whenSearchByRegular() throws IOException {
        FindFileArgs params = new FindFileArgs(new String[]{
                "-d", "root", "-n", "[a-zA-Z]+\\.[a-zA-Z]+", "-r", "-o", "out"
        });
        File success = folder.newFile("name.txt");
        File fail = folder.newFile("имя.тхт");
        Predicate<Path> func = params.searchFunc();
        assertTrue(func.test(success.toPath()));
        assertFalse(func.test(fail.toPath()));
    }
}