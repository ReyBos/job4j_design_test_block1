package ru.reybos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindFile {
    private static final Logger LOG = LoggerFactory.getLogger(FindFile.class.getName());

    public static void main(String[] args) {
        FindFileArgs params;
        try {
            params = new FindFileArgs(args);
        } catch (IllegalArgumentException e) {
            //сделать через лог
            LOG.error("Ошибка во входных данных", e);
            return;
        }
        System.out.println(params.rootDir());
    }
}
