package ru.bravery_and_stupidity.secretOfSatan.services;

import org.springframework.stereotype.Service;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;

import java.util.List;

@Service
public class UserServiceUnit implements UserService {

    @Override
    public void addUser(UserDao user) {

    }

    @Override
    public void updateUser(UserDao user) {

    }

    @Override
    public UserDao getUser(String login) {
        return null;
    }

    @Override
    public List<UserDao> getUsers() {
        return null;
    }

    @Override
    public void deleteUser(String login) {

    }

    @Override
    public void calculateTargets() {

    }

}
