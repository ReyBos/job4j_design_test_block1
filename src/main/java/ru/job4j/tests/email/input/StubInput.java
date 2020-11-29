package ru.job4j.tests.email.input;

import ru.job4j.tests.email.output.Output;

public class StubInput implements Input {
    private int point = 0;
    private String[] answers;
    private Output out;

    public StubInput(String[] answers, Output out) {
        this.answers = answers;
        this.out = out;
    }

    @Override
    public String askStr(String question) {
        out.println(question);
        return answers[point++];
    }

    @Override
    public int askInt(String question) {
        out.println(question);
        return Integer.parseInt(answers[point++]);
    }
}
