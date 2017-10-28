package ru.bravery_and_stupidity.secretOfSatan.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
final class UserAuthenticationData implements Serializable{
    @NotNull
    private String login = "";

    @NotNull
    private String password = "";

    UserAuthenticationData(){}

    UserAuthenticationData(@NotNull String login, @NotNull String password) {
        this.login = login;
        this.password = password;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }
}
