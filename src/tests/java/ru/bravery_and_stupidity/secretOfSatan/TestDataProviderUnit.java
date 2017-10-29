package ru.bravery_and_stupidity.secretOfSatan;

import org.jetbrains.annotations.NotNull;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDaoUnit;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserUnit;

final class TestDataProviderUnit implements TestDataProvider {

    @NotNull
    @Override
    public UserDao getSimpleUserDaoExample() {
        return buildNewUserDaoInstance();
    }

    @NotNull
    @Override
    public UserDao getInvalidUserDaoExample() {
        // TODO: develop this method
        return buildNewUserDaoInstance();
    }

    /**
     * this method must be the only place in test code, where implementation of UserDao is used directly.
     * all code must refer to interface name, but not to implementation name.
     */
    private UserDao buildNewUserDaoInstance() {
        return new UserDaoUnit();
    }

    @NotNull
    @Override
    public User getNewSimpleUserExample() {
        User user = buildNewUserInstance();
        user.setName("simple_user");
        user.setLogin("simple_user_login");
        user.setPassword("simple_user_password");
        user.setAdmin(false);
        user.setTarget("some_other_user");
        return user;
    }

    /**
     * this method must be the only place in test code, where implementation of User is used directly.
     * all code must refer to interface name, but not to implementation name.
     */
    private User buildNewUserInstance() {
        return new UserUnit();
    }

}
