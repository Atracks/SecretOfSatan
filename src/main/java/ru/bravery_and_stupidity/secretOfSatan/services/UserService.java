package ru.bravery_and_stupidity.secretOfSatan.services;

import ru.bravery_and_stupidity.secretOfSatan.dao.UserUnitDao;

import java.util.List;

public interface UserService {
    void addUser(UserUnitDao user);
    void updateUser(UserUnitDao user);
    UserUnitDao getUser(String login);
    List<UserUnitDao> getUsers();
    void deleteUser(String login);
    void calculateTargets();
}
