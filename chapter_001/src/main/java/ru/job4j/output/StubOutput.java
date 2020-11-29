package ru.job4j.output;

public class StubOutput implements Output {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void println(Object ob) {
        print(ob);
        buffer.append(System.lineSeparator());
    }

    @Override
    public void print(Object ob) {
        if (ob != null) {
            buffer.append(ob.toString());
        } else {
            buffer.append("null");
        }
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
