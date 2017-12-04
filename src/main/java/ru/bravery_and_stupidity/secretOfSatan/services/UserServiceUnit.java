package ru.bravery_and_stupidity.secretOfSatan.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidator;
import ru.bravery_and_stupidity.secretOfSatan.repository.UserRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

@Service
public final class UserServiceUnit implements UserService {

    private static final Random DICE = new Random();

    @Autowired
    private UserRepository repository;

    private UserValidator validator;

    UserServiceUnit(UserRepository repository, UserValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    @Transactional
    public void addUser(@NotNull UserDao userData) {
        User user = userData.mapToModel();
        requireValid(user);
        requireUnique(user);

        repository.saveUser(user);
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteUser(@NotNull String login) {
        requireValid(login);
        repository.deleteUser(login);
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

    @Override
    @Transactional
    public void calculateTargets() {
        List<User> users = repository.getUsers();
        if (users.size() < 2) {
            throw new IllegalStateException("unable to calculate targets: at least 2 users required");
        }

        boolean success = false;
        while (!success) {
            success = tryCalculateTargets(users);
        }
    }

    private boolean tryCalculateTargets(List<User> users) {
        List<User> unprocessed = new LinkedList<>(users);

        int upperBound = unprocessed.size();

        for (User each : users) {
            User target = roll(upperBound, unprocessed, each);
            if (null == target) {
                return false;
            }

            each.setTarget(target.getLogin());
            repository.saveUser(each);

            unprocessed.remove(target);
            --upperBound;
        }

        return true;
    }

    private User roll(int bound, List<User> availableUsers, User denied) {
        if (needRecalculate(bound, availableUsers, denied)) {
            return null;
        }

        User chosen;
        do {
            int random = DICE.nextInt(bound);
            chosen = availableUsers.get(random);
        } while (chosen.equals(denied));

        return chosen;
    }

    private boolean needRecalculate(int bound, List<User> availableUsers, User denied) {
        return (bound == 1) && (availableUsers.size() == 1) && denied.equals(availableUsers.get(0));
    }

}
