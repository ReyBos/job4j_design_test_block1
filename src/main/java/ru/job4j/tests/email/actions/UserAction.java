package ru.job4j.tests.email.actions;

import ru.job4j.tests.email.input.Input;
import ru.job4j.tests.email.Logic;

public interface UserAction {
    String name();

    boolean execute(Input input, Logic logic);
}
