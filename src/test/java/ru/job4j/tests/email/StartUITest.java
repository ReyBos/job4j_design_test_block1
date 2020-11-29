package ru.job4j.tests.email;

import org.junit.Test;
import ru.job4j.tests.email.actions.*;
import ru.job4j.tests.email.input.Input;
import ru.job4j.tests.email.input.StubInput;
import ru.job4j.tests.email.output.Output;
import ru.job4j.tests.email.output.StubOutput;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenInvalidMenuItem() {
        Logic logic = new Logic();
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"1", "0"}, out);
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction());
        new StartUI(out).init(in, logic, actions);
        String expected = "Меню:" + System.lineSeparator()
                + "0. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + "1 - неверный пункт меню, введите число от 0 до 0" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expected, out.toString());
    }

    @Test
    public void whenUsersEmpty() {
        Logic logic = new Logic();
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {"0", "1"}, out);
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ShowUsersAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, logic, actions);
        String expected = "Меню:" + System.lineSeparator()
                + "0. Исходные данные" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Исходные данные ----" + System.lineSeparator()
                + "Список пользователей пуст" + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Исходные данные" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expected, out.toString());
    }

    @Test
    public void whenAddUsersAndShowThem() {
        Logic logic = new Logic();
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {
                "0",
                "user1",
                "email1",
                "0",
                "1",
                "2"
        }, out);
        List<UserAction> actions = new ArrayList<>();
        actions.add(new AddUserAction(out));
        actions.add(new ShowUsersAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, logic, actions);
        String expected = "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Исходные данные" + System.lineSeparator()
                + "2. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Добавить пользователя ----" + System.lineSeparator()
                + "Введите имя: " + System.lineSeparator()
                + "для выхода введите 0" + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Пользователь успешно добавлен" + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Исходные данные" + System.lineSeparator()
                + "2. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Исходные данные ----" + System.lineSeparator()
                + "user1: email1 " + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Исходные данные" + System.lineSeparator()
                + "2. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expected, out.toString());
    }

    @Test
    public void whenAddUsersError() {
        Logic logic = new Logic();
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {
                "0",
                "user1",
                "email1",
                "0",
                "0",
                "user1",
                "0",
                "user2",
                "0",
                "1"
        }, out);
        List<UserAction> actions = new ArrayList<>();
        actions.add(new AddUserAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, logic, actions);
        String expected = "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Добавить пользователя ----" + System.lineSeparator()
                + "Введите имя: " + System.lineSeparator()
                + "для выхода введите 0" + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Пользователь успешно добавлен" + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Добавить пользователя ----" + System.lineSeparator()
                + "Введите имя: " + System.lineSeparator()
                + "Ошибка: пользователь с таким именем существует" + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Добавить пользователя ----" + System.lineSeparator()
                + "Введите имя: " + System.lineSeparator()
                + "для выхода введите 0" + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Пользователь не добавлен, не введен ни один email" + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expected, out.toString());
    }

    @Test
    public void whenMergeUsers() {
        Logic logic = new Logic();
        Output out = new StubOutput();
        Input in = new StubInput(new String[] {
                "0",
                "user1",
                "email1",
                "0",
                "0",
                "user2",
                "email1",
                "0",
                "1",
                "2"
        }, out);
        List<UserAction> actions = new ArrayList<>();
        actions.add(new AddUserAction(out));
        actions.add(new MergeUserAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, logic, actions);
        String expected = "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Объединить пользователей" + System.lineSeparator()
                + "2. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Добавить пользователя ----" + System.lineSeparator()
                + "Введите имя: " + System.lineSeparator()
                + "для выхода введите 0" + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Пользователь успешно добавлен" + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Объединить пользователей" + System.lineSeparator()
                + "2. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Добавить пользователя ----" + System.lineSeparator()
                + "Введите имя: " + System.lineSeparator()
                + "для выхода введите 0" + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Введите email: " + System.lineSeparator()
                + "Пользователь успешно добавлен" + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Объединить пользователей" + System.lineSeparator()
                + "2. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator() + "---- Объединенные пользователи ----" + System.lineSeparator()
                + "user1: email1 " + System.lineSeparator()
                + System.lineSeparator() + "Меню:" + System.lineSeparator()
                + "0. Добавить пользователя" + System.lineSeparator()
                + "1. Объединить пользователей" + System.lineSeparator()
                + "2. Выход" + System.lineSeparator()
                + "Выберите пункт меню: " + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expected, out.toString());
    }
}