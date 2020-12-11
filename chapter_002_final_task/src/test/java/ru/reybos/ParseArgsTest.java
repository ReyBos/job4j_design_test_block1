package ru.reybos;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParseArgsTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenNoArgs() {
        ParseArgs.of(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegalArgs() {
        ParseArgs.of(new String[]{"-key", "val", "val2", "val1"});
    }

    @Test
    public void whenSuccess() {
        ParseArgs params = ParseArgs.of(new String[]{"-key", "val", "-key1", "-key2", "val2"});
        assertThat(params.get("-key"), is("val"));
        assertNull(params.get("-key1"));
        assertThat(params.get("-key2"), is("val2"));
    }
}