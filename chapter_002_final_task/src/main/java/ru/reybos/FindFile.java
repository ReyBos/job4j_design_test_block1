package ru.reybos;

public class FindFile {
    public static void main(String[] args) {
        ProgramArgs params;
        try {
            params = new ProgramArgs(args);
        } catch (IllegalArgumentException e) {
            //сделать через лог
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(params.getRootDir());
    }
}
