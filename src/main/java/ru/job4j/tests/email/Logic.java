package ru.job4j.tests.email;

import ru.job4j.tests.email.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Logic {
    private List<User> users;
    private List<User> mergedUsers;

    public Logic() {
        this.users = new ArrayList<>();
        this.mergedUsers = new ArrayList<>();
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public User findUserByName(String name) {
        int index = users.indexOf(new User(name));
        return index != -1 ? users.get(index) : null;
    }

    public String showUsers() {
        return getUsersString(users);
    }

    /**
     * Делаем словарь емейл -> пользователь и проверяем емейл каждого исходного пользователя
     * на присутствие в этом словаре. Если емейл уже есть в словаре, значит ранее был пользователь
     * с таким емейлом и нужно их объеденить, иначе это уникальный пользователь и добавляем все
     * его емейлы в этот словарь.
     */
    public String mergeUsers() {
        if (users.size() != 0) {
            mergedUsers = new ArrayList<>();
            HashMap<String, User> emailUserMap = new HashMap<>();
            User currUser, mergedUser;
            for (User user: users) {
                currUser = new User(user.getName());
                Set<String> emails = user.getEmails();
                for (String email : emails) {
                    mergedUser = emailUserMap.get(email);
                    if (mergedUser != null) {
                        currUser = mergedUser;
                        break;
                    }
                }
                for (String email : emails) {
                    currUser.addEmail(email);
                    emailUserMap.put(email, currUser);
                }
                if (!mergedUsers.contains(currUser)) {
                    mergedUsers.add(currUser);
                }
            }
        }
        return getUsersString(mergedUsers);
    }

    private String getUsersString(List<User> users) {
        StringBuilder rsl = new StringBuilder();
        if (users.size() == 0) {
            rsl.append("Список пользователей пуст");
            rsl.append(System.lineSeparator());
        } else {
            for (User user : users) {
                rsl.append(user);
                rsl.append(System.lineSeparator());
            }
        }
        return rsl.toString();
    }
}
