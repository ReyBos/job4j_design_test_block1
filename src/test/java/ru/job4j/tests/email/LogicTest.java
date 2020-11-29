package ru.job4j.tests.email;

import org.junit.Test;
import ru.job4j.tests.email.models.User;

import java.util.TreeSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LogicTest {
    @Test
    public void whenEmptyUsers() {
        Logic logic = new Logic();
        assertThat(logic.showUsers(), is(
                "Список пользователей пуст" + System.lineSeparator()
        ));
        assertThat(logic.mergeUsers(), is(
                "Список пользователей пуст" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowUsers() {
        Logic logic = fillLogic();
        String out = logic.showUsers();
        String expected = "user1: email1 email2 " + System.lineSeparator()
                + "user2: email2 email3 " + System.lineSeparator()
                + "user3: email email4 " + System.lineSeparator();
        assertEquals(out, expected);
    }

    @Test
    public void whenMergeUsers() {
        Logic logic = fillLogic();
        String out = logic.mergeUsers();
        String expected = "user1: email1 email2 email3 " + System.lineSeparator()
                + "user3: email email4 " + System.lineSeparator();
        assertEquals(expected, out);
    }

    private Logic fillLogic() {
        Logic logic = new Logic();
        TreeSet<String> email = new TreeSet<>();
        email.add("email2");
        email.add("email1");
        User user1 = new User("user1", email);
        TreeSet<String> email2 = new TreeSet<>();
        email2.add("email2");
        email2.add("email3");
        User user2 = new User("user2", email2);
        TreeSet<String> email3 = new TreeSet<>();
        email3.add("email");
        email3.add("email4");
        User user3 = new User("user3", email3);
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addUser(user3);
        return logic;
    }

    @Test
    public void whenTaskExample() {
        Logic logic = new Logic();
        TreeSet<String> email = new TreeSet<>();
        email.add("xxx@ya.ru");
        email.add("foo@gmail.com");
        email.add("lol@mail.ru");
        User user1 = new User("user1", email);
        TreeSet<String> email2 = new TreeSet<>();
        email2.add("foo@gmail.com");
        email2.add("ups@pisem.net");
        User user2 = new User("user2", email2);
        TreeSet<String> email3 = new TreeSet<>();
        email3.add("xyz@pisem.net");
        email3.add("vasya@pupkin.com");
        User user3 = new User("user3", email3);
        TreeSet<String> email4 = new TreeSet<>();
        email4.add("ups@pisem.net");
        email4.add("aaa@bbb.ru");
        User user4 = new User("user4", email4);
        TreeSet<String> email5 = new TreeSet<>();
        email5.add("xyz@pisem.net");
        User user5 = new User("user5", email5);
        logic.addUser(user1);
        logic.addUser(user2);
        logic.addUser(user3);
        logic.addUser(user4);
        logic.addUser(user5);
        String out = logic.mergeUsers();
        String expected = "user1: aaa@bbb.ru foo@gmail.com lol@mail.ru ups@pisem.net xxx@ya.ru "
                + System.lineSeparator()
                + "user3: vasya@pupkin.com xyz@pisem.net "
                + System.lineSeparator();
        assertEquals(expected, out);
    }
}