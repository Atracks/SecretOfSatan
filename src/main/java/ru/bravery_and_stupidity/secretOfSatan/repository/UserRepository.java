package ru.bravery_and_stupidity.secretOfSatan.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bravery_and_stupidity.secretOfSatan.model.User;

import java.util.List;

public interface UserRepository {

    void saveUser(@NotNull User user);

    @Nullable
    User getUser(@NotNull String login, @NotNull String password);

    List<User> getUsers();

    void deleteUser(int userId);
}
