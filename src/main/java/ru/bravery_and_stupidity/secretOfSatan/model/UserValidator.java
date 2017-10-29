package ru.bravery_and_stupidity.secretOfSatan.model;


import org.jetbrains.annotations.Nullable;

public interface UserValidator {

    boolean isWrong(@Nullable User user);

    boolean isWrong(@Nullable String login, @Nullable String password);

}
