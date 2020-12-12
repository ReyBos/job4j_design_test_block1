package ru.reybos;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверяем наличие одного из параметров (FULL_NAME, MASK, REGULAR) в парсере аргументов которые
 * пришли при запуске программы (parser). В зависимости от того какой параметр присутствует в
 * парсере в SearchFunc::func помещаем Predicate по которому будем искать нужное название файла
 * при обходе папок
 */
public class SearchFunc {
    private final Map<Function<String, Boolean>, Consumer<SearchFunc>> dispatch;
    private final ParseArgs parser;
    private final String searchedFileName;
    private Predicate<Path> func;

    public static final String FULL_NAME = "-f";
    public static final String MASK = "-m";
    public static final String REGULAR = "-r";

    public SearchFunc(ParseArgs parser, String searchedFileName) {
        this.parser = parser;
        this.searchedFileName = searchedFileName;
        dispatch = new HashMap<>();
        dispatch.put(
                s -> s.equals(FULL_NAME) && this.parser.hasKey(s),
                byFullName()
        );
        dispatch.put(
                s -> s.equals(MASK) && this.parser.hasKey(s),
                byMask()
        );
        dispatch.put(
                s -> s.equals(REGULAR) && this.parser.hasKey(s),
                byRegular()
        );
    }

    public void checkParam(String param) {
        for (Function<String, Boolean> predict : this.dispatch.keySet()) {
            if (predict.apply(param)) {
                this.dispatch.get(predict).accept(this);
            }
        }
    }

    /**
     * Будем искать файл по полному совпадению имени и переданного параметра
     */
    public Consumer<SearchFunc> byFullName() {
        return searchFunc -> searchFunc.setFunc(
                path -> path.toFile().getName().equals(searchFunc.getSearchedFileName())
        );
    }

    /**
     * Поиск файла по маске *.type
     */
    public Consumer<SearchFunc> byMask() {
        return searchFunc -> searchFunc.setFunc(
                path -> path.toFile().getName().endsWith(
                        searchFunc.getSearchedFileName().substring(1)
                )
        );
    }

    /**
     * Поиск файла по регулярному выражению
     */
    public Consumer<SearchFunc> byRegular() {
        return searchFunc -> searchFunc.setFunc(
                path -> {
                    Pattern pattern = Pattern.compile(searchFunc.getSearchedFileName());
                    Matcher matcher = pattern.matcher(path.toFile().getName());
                    return matcher.find();
                }
        );
    }

    public void setFunc(Predicate<Path> func) {
        this.func = func;
    }

    public Predicate<Path> getFunc() {
        return func;
    }

    public String getSearchedFileName() {
        return searchedFileName;
    }
}
