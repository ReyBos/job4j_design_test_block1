package ru.reybos;

import org.junit.Before;
import org.junit.Test;
import ru.reybos.model.Company;
import ru.reybos.model.Person;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class StoreSQLTest {
    public Connection init() {
        try (InputStream in = StoreSQL.class
                .getClassLoader()
                .getResourceAsStream("app.properties")
        ) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return ConnectionRollback.create(DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            ));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void addData(StoreSQL<Company> storeCompany, StoreSQL<Person> storePerson)
            throws SQLException {
        String sqlCompany = "insert into company(name) values (?)";
        BiConsumerException<Company, PreparedStatement> funcCompany = (company, statement) -> {
            statement.setString(1, company.getName());
        };
        storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 1"));
        storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 2"));
        storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 3"));
        storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 4"));
        storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 5"));
        storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 6"));
        String sqlPerson = "insert into person(name, company_id) values (?, ?)";
        BiConsumerException<Person, PreparedStatement> funcPerson = (person, statement) -> {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getCompanyID());
        };
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 1", 1));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 2", 1));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 3", 2));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 4", 3));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 5", 4));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 6", 5));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 7", 6));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 8", 1));
        storePerson.insert(sqlPerson, funcPerson, new Person("Работник 9", 4));
    }

    @Test
    public void whenNotInCompanyID() throws Exception {
        try (Connection cn = this.init()) {
            StoreSQL<Company> storeCompany = new StoreSQL<>(cn);
//            StoreSQL<Person> storePerson = new StoreSQL<>(cn);
//            this.addData(storeCompany, storePerson);
            String sqlCompany = "insert into company(name) values (?)";
            BiConsumerException<Company, PreparedStatement> funcCompany = (company, statement) -> {
                statement.setString(1, company.getName());
            };
            storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 1"));
            storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 2"));
            storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 3"));
            storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 4"));
            storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 5"));
            storeCompany.insert(sqlCompany, funcCompany, new Company("Фирма 6"));
            String sqlCompanyGet = "select * from company";
            BiConsumerException<Company, PreparedStatement> funcCompany2 = (company, statement) -> { };
            BiConsumerException<List<Company>, ResultSet> saveRsl = (companies, resultSet) -> {
                companies.add(new Company(resultSet.getInt("id"), resultSet.getString("name")));
            };
            List<Company> rslCompany = storeCompany.select(sqlCompanyGet, funcCompany2, null, saveRsl);
            for (Company company : rslCompany) {
                System.out.println(company.getId() + company.getName());
            }
        }
    }
}