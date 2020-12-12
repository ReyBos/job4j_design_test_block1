package ru.reybos;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FindFileTest {
    private static final Logger LOG = LoggerFactory.getLogger(FindFile.class.getName());

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void main() throws IOException {
        folder.newFolder("folder1");
        folder.newFile("folder1/inFolder1.txt");
        folder.newFile("text.txt");
        folder.newFile("doc.doc");
        File rsl = folder.newFile("result.txt");
        FindFile.main(new String[]{
                "-d", folder.getRoot().getAbsolutePath(), "-n", "*.txt", "-m", "-o", rsl.getAbsolutePath()
        });
        List<String> files = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(rsl.getAbsolutePath(), StandardCharsets.UTF_8)
        )) {
            reader.lines().forEach(files::add);
        }
        List<String> expected = List.of(
                "inFolder1.txt",
                "result.txt",
                "text.txt"
        );
        files.sort(String::compareTo);
        assertThat(files, is(expected));
    }
}