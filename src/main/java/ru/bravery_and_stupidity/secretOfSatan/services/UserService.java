package ru.bravery_and_stupidity.secretOfSatan.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;

import java.util.List;

public interface UserService {

    void addUser(@NotNull UserDao user);

    void updateUser(@NotNull UserDao user);

    @Nullable
    UserDao getUser(@NotNull String login);

    @NotNull
    List<UserDao> getUsers();

    void deleteUser(@NotNull String login);

    void calculateTargets();

    boolean isRegistrationAllowed();

}
