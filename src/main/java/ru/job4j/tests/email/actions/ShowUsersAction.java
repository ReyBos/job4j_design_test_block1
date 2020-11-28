package ru.job4j.tests.email.actions;

import ru.job4j.tests.email.input.Input;
import ru.job4j.tests.email.Logic;
import ru.job4j.tests.email.output.Output;

public class ShowUsersAction implements UserAction {
    Output out;

    public ShowUsersAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Исходные данные";
    }

    @Override
    public boolean execute(Input input, Logic logic) {
        out.println("---- " + name() + " ----");
        out.print(logic.showUsers());
        return true;
    }
}
