package ru.job4j.tests.email;

import ru.job4j.tests.email.actions.*;
import ru.job4j.tests.email.input.ConsoleInput;
import ru.job4j.tests.email.input.Input;
import ru.job4j.tests.email.output.ConsoleOutput;
import ru.job4j.tests.email.output.Output;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    private void init(Input in, Logic logic, List<UserAction> actions) {
        boolean run = true;
        int actionIndex;
        while (run) {
            showMenu(actions);
            boolean incorrectAction;
            do {
                actionIndex = in.askInt("Выберите пункт меню: ");
                incorrectAction = actionIndex < 0 || actionIndex >= actions.size();
                if (incorrectAction) {
                    out.println(
                            actionIndex
                            + " - неверный пункт меню, введите число от 0 до "
                            + (actions.size() - 1)
                    );
                }
            } while (incorrectAction);
            out.println("");
            run = actions.get(actionIndex).execute(in, logic);
            if (run) {
                out.println("");
            }
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Меню:");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input in = new ConsoleInput(out);
        Logic logic = new Logic();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new AddUserAction(out));
        actions.add(new ShowUsersAction(out));
        actions.add(new MergeUserAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, logic, actions);
    }
}
