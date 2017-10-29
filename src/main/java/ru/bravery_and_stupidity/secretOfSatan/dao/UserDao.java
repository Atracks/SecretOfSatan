package ru.bravery_and_stupidity.secretOfSatan.dao;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.jetbrains.annotations.NotNull;

@JsonNaming
public class UserDao {

    @NotNull
    private String login = "";

    @NotNull
    private String password = "";

    @NotNull
    private String name = "";

    @NotNull
    private String desire = "";

    @NotNull
    private String targetLogin = "";

    private boolean isAdmin = false;

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setDesire(@NotNull String desire) {
        this.desire = desire;
    }

    public void setTarget(@NotNull String targetLogin) {
        this.targetLogin = targetLogin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getDesire() {
        return desire;
    }

    @NotNull
    public String getTarget() {
        return targetLogin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
