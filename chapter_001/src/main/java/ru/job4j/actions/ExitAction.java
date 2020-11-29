package ru.job4j.actions;

import ru.job4j.Logic;
import ru.job4j.input.Input;

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
