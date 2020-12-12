package ru.reybos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FindFile {
    private static final Logger LOG = LoggerFactory.getLogger(FindFile.class.getName());

    public static void main(String[] args) throws IOException {
        FindFileArgs params;
        try {
            params = new FindFileArgs(args);
        } catch (IllegalArgumentException e) {
            //сделать через лог
            LOG.error("Ошибка во входных данных", e);
            return;
        }
        Path root = Paths.get(params.rootDir());
        SearchFileVisitor visitor = new SearchFileVisitor(params.searchFunc());
        Files.walkFileTree(root, visitor);
        List<String> rsl = visitor.getPathNames();
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(params.outFile())),
                true,
                StandardCharsets.UTF_8
        )) {
            for (String fileName : rsl) {
                out.println(fileName);
            }
        } catch (Exception e) {
            LOG.error("Ошибка записи результата", e);
        }
    }
}
