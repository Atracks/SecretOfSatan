package ru.bravery_and_stupidity.secretOfSatan.dao;

import org.jetbrains.annotations.NotNull;

public interface UserDao {

    void setLogin(@NotNull String login);

    void setPassword(@NotNull String password);

    void setName(@NotNull String name);

    void setDesire(@NotNull String desire);

    void setTarget(@NotNull String targetLogin);

    void setAdmin(boolean admin);

    @NotNull
    String getName();

    @NotNull
    String getLogin();

    @NotNull
    String getPassword();

    @NotNull
    String getDesire();

    @NotNull
    String getTarget();

    boolean isAdmin();

}
