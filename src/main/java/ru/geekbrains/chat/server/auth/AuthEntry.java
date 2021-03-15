package ru.geekbrains.chat.server.auth;

import java.util.Objects;

public class AuthEntry {
    private String login;
    private String password;
    private String nickName;

    public AuthEntry(String login, String password, String nickName) {
        this.login = login;
        this.password = password;
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthEntry authEntry = (AuthEntry) o;
        return Objects.equals(login, authEntry.login) && Objects.equals(password, authEntry.password) && Objects.equals(nickName, authEntry.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, nickName);
    }
}
