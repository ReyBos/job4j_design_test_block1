package ru.job4j.tests.email.input;

import ru.job4j.tests.email.output.Output;

import java.util.Scanner;

public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);
    Output out;

    public ConsoleInput(Output out) {
        this.out = out;
    }

    @Override
    public String askStr(String question) {
        out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int rsl = -1;
        do {
            try {
                rsl = Integer.parseInt(askStr(question));
                invalid = false;
            } catch (NumberFormatException e) {
                out.println("Пожалуйста введите число");
            }
        } while (invalid);
        return rsl;
    }
}
