package ru.job4j.actions;

import ru.job4j.Logic;
import ru.job4j.input.Input;

public interface UserAction {
    String name();

    boolean execute(Input input, Logic logic);
}
