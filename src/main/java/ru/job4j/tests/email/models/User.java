package ru.job4j.tests.email.models;

import java.util.*;

public class User {
    private String name;
    private Set<String> emails;

    public User(String name) {
        this.name = name;
        this.emails = new HashSet<>();
    }

    public void addEmail(String email) {
        this.emails.add(email);
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmails() {
        return emails;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(name + ": ");
        for (String email : emails) {
            str.append(email);
            str.append(" ");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
