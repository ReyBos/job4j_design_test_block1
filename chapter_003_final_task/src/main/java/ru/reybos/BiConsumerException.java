package ru.reybos;

import java.sql.SQLException;

public interface BiConsumerException<T, U> {
    void accept(T t, U u) throws SQLException;
}
