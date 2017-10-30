package ru.bravery_and_stupidity.secretOfSatan.services;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidator;
import ru.bravery_and_stupidity.secretOfSatan.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public final class UserServiceUnit implements UserService {

    private UserRepository repository;

    private UserValidator validator;

    private static final Logger log = Logger.getLogger(UserServiceUnit.class);

    UserServiceUnit(UserRepository repository, UserValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public void addUser(@NotNull UserDao userData) {
        User user = userData.mapToModel();
        requireValid(user);
        requireUnique(user);

        repository.saveUser(user);
    }

    @Override
    public void updateUser(@NotNull UserDao userData) {
        User user = userData.mapToModel();
        requireValid(user);
        requireExist(user);

        repository.saveUser(user);
    }

    private void requireValid(User user) {
        if (validator.isWrong(user)) {
            String diagnostics = "entered data is invalid: " + user.toString();
            throw new IllegalArgumentException(diagnostics);
        }
    }

    private void requireUnique(User user) {
        if (isUserExist(user)) {
            String diagnostics = "login \"" + user.getLogin() + "\" is already in use";
            throw new IllegalArgumentException(diagnostics);
        }
    }

    private void requireExist(User user) {
        if (!isUserExist(user)) {
            String diagnostics = "account with login \"" + user.getLogin() + "\" is not exist";
            throw new IllegalArgumentException(diagnostics);
        }
    }

    private boolean isUserExist(User user) {
        String login = user.getLogin();
        User userWithSameLogin = repository.getUser(login);
        return (userWithSameLogin != null);
    }

    @Override
    public UserDao getUser(@NotNull String login) {
        return null;
    }

    @NotNull
    @Override
    public List<UserDao> getUsers() {
        return Collections.emptyList();
    }

    @Override
    public void deleteUser(@NotNull String login) {

    }

    @Override
    public void calculateTargets() {

    }

}
