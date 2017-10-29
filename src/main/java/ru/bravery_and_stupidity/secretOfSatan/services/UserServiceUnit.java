package ru.bravery_and_stupidity.secretOfSatan.services;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public final class UserServiceUnit implements UserService {

    private UserRepository repository;

    private static final Logger log = Logger.getLogger(UserServiceUnit.class);

    public UserServiceUnit(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addUser(@NotNull UserDao user) {

    }

    @Override
    public void updateUser(@NotNull UserDao user) {

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
