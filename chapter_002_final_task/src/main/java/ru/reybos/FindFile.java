package ru.reybos;

public class FindFile {
    public static void main(String[] args) {
        FindFileArgs params;
        try {
            params = new FindFileArgs(args);
        } catch (IllegalArgumentException e) {
            //сделать через лог
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(params.rootDir());
    }
}
