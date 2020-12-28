package ru.reybos;

import ru.reybos.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class StoreSQL<T extends Model> {
    private Connection cn;

    public StoreSQL(Connection cn) {
        this.cn = cn;
    }

    public void insert(
            String sql,
            BiConsumerException<T, PreparedStatement> func,
            T model
    ) throws SQLException {
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            func.accept(model, statement);
            statement.execute();
        }
    }

    public List<T> select(
            String sql,
            BiConsumerException<T, PreparedStatement> func,
            T model,
            BiConsumerException<List<T>, ResultSet> saveRsl
    ) throws SQLException {
        List<T> rsl = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            func.accept(model, statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    saveRsl.accept(rsl, resultSet);
                }
            }
        }
        return rsl;
    }
}
