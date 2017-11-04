package ru.bravery_and_stupidity.secretOfSatan.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidator;
import ru.bravery_and_stupidity.secretOfSatan.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public final class UserServiceUnit implements UserService {

    private UserRepository repository;

    private UserValidator validator;

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

    @Nullable
    @Override
    public UserDao getUser(@NotNull String login) {
        requireValid(login);

        User user = repository.getUser(login);
        return (null == user) ? null : user.mapToDao();
    }

    @NotNull
    @Override
    public List<UserDao> getUsers() {
        List<User> users = repository.getUsers();
        return mapToDao(users);
    }

    private List<UserDao> mapToDao(List<User> users) {
        List<UserDao> usersData = new ArrayList<>(15);
        for (User eachUser : users) {
            usersData.add(eachUser.mapToDao());
        }
        return usersData;
    }

    @Override
    public void deleteUser(@NotNull String login) {
        requireValid(login);
        repository.deleteUser(login);
    }

    @Override
    public void calculateTargets() {

    }

    private void requireValid(User user) {
        if (validator.isWrong(user)) {
            String diagnostics = "entered data is invalid: " + user.toString();
            throw new IllegalArgumentException(diagnostics);
        }
    }

    private void requireValid(String login) {
        if (validator.isWrong(login)) {
            String diagnostics = "login is invalid: " + login;
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
        User userWithSpecifiedLogin = repository.getUser(login);
        return (userWithSpecifiedLogin != null);
    }

}
