package ru.bravery_and_stupidity.secretOfSatan;

import org.jetbrains.annotations.NotNull;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;

final class TestDataProviderUnit implements TestDataProvider {

    @NotNull
    @Override
    public UserDao getSimpleUserDaoExample() {
        return new UserDao();
    }

    @NotNull
    @Override
    public UserDao getInvalidUserDaoExample() {
        // TODO: develop this method
        return new UserDao();
    }

    @NotNull
    @Override
    public User getNewSimpleUserExample() {
        User user = new User();
        user.setName("simple_user");
        user.setLogin("simple_user_login");
        user.setPassword("simple_user_password");
        user.setAdmin(false);
        user.setTarget("some_other_user");
        return user;
    }

}
