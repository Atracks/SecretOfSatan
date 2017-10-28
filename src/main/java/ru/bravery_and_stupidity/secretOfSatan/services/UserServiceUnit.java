package ru.bravery_and_stupidity.secretOfSatan.services;

import org.springframework.stereotype.Service;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserUnitDao;

import java.util.List;

@Service
public class UserServiceUnit implements UserService {
    @Override
    public void addUser(UserUnitDao user) {

    }

    @Override
    public void updateUser(UserUnitDao user) {

    }

    @Override
    public UserUnitDao getUser(String login) {
        return null;
    }

    @Override
    public List<UserUnitDao> getUsers() {
        return null;
    }

    @Override
    public void deleteUser(String login) {

    }

    @Override
    public void calculateTargets() {

    }
}
