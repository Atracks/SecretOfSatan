package ru.bravery_and_stupidity.secretOfSatan.services;

import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;

import java.util.List;

public interface UserService {

    void addUser(UserDao user);

    void updateUser(UserDao user);

    UserDao getUser(String login);

    List<UserDao> getUsers();

    void deleteUser(String login);

    void calculateTargets();

}
