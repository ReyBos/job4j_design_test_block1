package ru.job4j.tests.email.actions;

import ru.job4j.tests.email.*;
import ru.job4j.tests.email.input.Input;
import ru.job4j.tests.email.models.User;
import ru.job4j.tests.email.output.Output;

public class AddUserAction implements UserAction {
    Output out;

    public AddUserAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Добавить пользователя";
    }

    @Override
    public boolean execute(Input input, Logic logic) {
        out.println("---- " + name() + " ----");
        String name = input.askStr("Введите имя: ");
        String msg;
        if (logic.findUserByName(name) == null) {
            User user = new User(name);
            out.println("для выхода введите 0");
            do {
                String email = input.askStr("Введите email: ");
                if (email.equals("0") || email.equals("")) {
                    break;
                }
                user.addEmail(email);
            } while (true);
            if (user.getEmails().size() == 0) {
                msg = "Пользователь не добавлен, не введен ни один email";
            } else if (logic.addUser(user)) {
                msg = "Пользователь успешно добавлен";
            } else {
                msg = "Ошибка добавления пользователя";
            }
        } else {
            msg = "Ошибка: пользователь с таким именем существует";
        }
        out.println(msg);
        return true;
    }
}