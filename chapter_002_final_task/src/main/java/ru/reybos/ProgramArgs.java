package ru.reybos;

public class ProgramArgs {
    private final ParseArgs parser;
    private final StringBuilder error;

    public ProgramArgs(String[] args) {
        parser = ParseArgs.of(args);
        error = new StringBuilder();
        valid();
    }

    private void valid() {
        try {
            getRootDir();
        } catch (IllegalArgumentException e) {
            error.append(e.getMessage());
            error.append(System.lineSeparator());
        }
        if (error.length() != 0) {
            throw new IllegalArgumentException(error.toString());
        }
    }

    public String getRootDir() {
        return checkParam("-d");
    }

    private String checkParam(String name) {
        if (parser.get(name) == null) {
            throw new IllegalArgumentException("Не передан параметр " + name);
        }
        return parser.get(name);
    }
}
