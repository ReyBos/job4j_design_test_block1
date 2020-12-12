package ru.reybos;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Тут проверяем что при старте программы пришли валидные параметры и
 * для каждого из них возвращаем нужные значения
 */
public class FindFileArgs {
    private final ParseArgs parser;
    private final StringBuilder error;

    public FindFileArgs(String[] args) {
        parser = ParseArgs.of(args);
        error = new StringBuilder();
        valid();
    }

    private void valid() {
        Supplier<String> rootDir = this::rootDir;
        checkMethod(rootDir);
        Supplier<String> searchedFileName = this::searchedFileName;
        checkMethod(searchedFileName);
        Supplier<String> outFile = this::outFile;
        checkMethod(outFile);
        try {
            searchFunc();
        } catch (IllegalArgumentException e) {
            error.append(e.getMessage());
            error.append(System.lineSeparator());
        }
        if (error.length() != 0) {
            throw new IllegalArgumentException(error.toString());
        }
    }

    private void checkMethod(Supplier<String> func) {
        try {
            func.get();
        } catch (IllegalArgumentException e) {
            error.append(e.getMessage());
            error.append(System.lineSeparator());
        }
    }

    public String rootDir() {
        return checkParam("-d");
    }

    public String searchedFileName() {
        return checkParam("-n");
    }

    public String outFile() {
        return checkParam("-o");
    }

    private String checkParam(String name) {
        if (parser.get(name) == null) {
            throw new IllegalArgumentException("Не передан параметр " + name);
        }
        return parser.get(name);
    }

    /**
     * Программой поддерживается три разных варианта поиска файлов по имени в зависимости
     * от того какой параметр был передан
     */
    public Predicate<Path> searchFunc() {
        SearchFunc searchFunc = new SearchFunc(this.parser, this.searchedFileName());
        searchFunc.checkParam(SearchFunc.FULL_NAME);
        searchFunc.checkParam(SearchFunc.MASK);
        searchFunc.checkParam(SearchFunc.REGULAR);
        if (searchFunc.getFunc() == null) {
            throw new IllegalArgumentException("Не передан один из параметров "
                    + SearchFunc.FULL_NAME + "/"
                    + SearchFunc.MASK + "/"
                    + SearchFunc.REGULAR
            );
        }
        return searchFunc.getFunc();
    }
}
