package ru.reybos;

import java.util.HashMap;
import java.util.Map;

/**
 * Тут разбираем параметры пришедшие при запуске программы
 */
public class ParseArgs {
    private final Map<String, String> params = new HashMap<>();

    public String get(String key) {
        return params.get(key);
    }

    public boolean hasKey(String key) {
        return params.containsKey(key);
    }

    /**
     * Предполагается что в программу пришли аргументы и что два (и более) ключей могут идти друг
     * за другом (например "-key1 -key2 val2"), а два (и более) значений нет
     * (например "-key1 val1 val2"), иначе кидаем исключение IllegalArgumentException()
     */
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не переданы параметры для запуска программы");
        }
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("-")) {
                throw new IllegalArgumentException(
                        "Ошибка в переданных параметрах. Принимаются параметры вида: "
                        + System.lineSeparator()
                        + "\"-ключ значение -ключ значение ...\" или "
                        + System.lineSeparator()
                        + "\"-ключ -ключ значение -ключ значение...\""
                );
            }
            if (i + 1 != args.length && !args[i + 1].startsWith("-")) {
                params.put(args[i], args[i + 1]);
                i++;
            } else {
                params.put(args[i], null);
            }
        }
    }

    public static ParseArgs of(String[] args) {
        ParseArgs params = new ParseArgs();
        params.parse(args);
        return params;
    }
}
