package ru.bravery_and_stupidity.secretOfSatan.model;

import org.jetbrains.annotations.NotNull;


public interface User {

    void setName(@NotNull String name);

    void setLogin(@NotNull String login);

    void setPassword(@NotNull String password);

    void setDesire(@NotNull String desire);

    void setAdmin(boolean admin);

    void setTarget(String target);

    @NotNull
    String getName();

    @NotNull
    String getLogin();

    @NotNull
    String getPassword();

    @NotNull
    String getDesire();

    boolean isAdmin();

    String getTarget();

}
