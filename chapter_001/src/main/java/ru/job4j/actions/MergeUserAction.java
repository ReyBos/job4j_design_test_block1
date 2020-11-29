package ru.job4j.actions;

import ru.job4j.Logic;
import ru.job4j.input.Input;
import ru.job4j.output.Output;

public class MergeUserAction implements UserAction {
    Output out;

    public MergeUserAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Объединить пользователей";
    }

    @Override
    public boolean execute(Input input, Logic logic) {
        out.println("---- Объединенные пользователи ----");
        out.print(logic.mergeUsers());
        return true;
    }
}
