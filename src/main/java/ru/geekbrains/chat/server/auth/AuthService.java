package ru.geekbrains.chat.server.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthService {
    private final List<AuthEntry> entries = new ArrayList<AuthEntry>(Arrays.asList(
            new AuthEntry("l1", "p1", "NickName1"),
            new AuthEntry("l2", "p2", "NickName2"),
            new AuthEntry("l3", "p3", "NickName3")
    ));



    public AuthEntry findUserByCredentials(String login, String password){
        for (AuthEntry entry : entries) {
            if(entry.getLogin().equals(login) && entry.getPassword().equals(password)){
                return entry;
            }
        }

        return null;
    }
}
