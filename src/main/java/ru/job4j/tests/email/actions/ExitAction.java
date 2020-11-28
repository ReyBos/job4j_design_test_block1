package ru.job4j.tests.email.actions;

import ru.job4j.tests.email.input.Input;
import ru.job4j.tests.email.Logic;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "Выход";
    }

    @Override
    public boolean execute(Input input, Logic logic) {
        return false;
    }
}
