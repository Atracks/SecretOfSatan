package ru.bravery_and_stupidity.secretOfSatan;

import org.jetbrains.annotations.NotNull;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidator;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidatorUnit;

final class TestDataProviderUnit implements TestDataProvider {

    @NotNull
    @Override
    public UserDao getSimpleUserDaoExample() {
        return createNewUserDaoExample();
    }

    @NotNull
    @Override
    public UserDao getInvalidUserDaoExample() {
        UserDao user = createNewUserDaoExample();
        user.setName("!@#$%^&*()");
        return user;
    }

    private UserDao createNewUserDaoExample() {
        UserDao user = new UserDao();
        user.setLogin("simpleLogin");
        user.setPassword("simplePassword");
        user.setName("simpleName");
        user.setDesire("simpleDesire1, simpleDesire2, simpleDesire3");
        user.setTarget("");
        return user;
    }

    @NotNull
    @Override
    public User getSimpleUserExample() {
        User user = new User();
        user.setLogin("simple_user_login");
        user.setName("simple_user");
        user.setPassword("simple_user_password");
        user.setTarget("some_other_user");
        user.setAdmin(false);
        return user;
    }

    @NotNull
    @Override
    public UserValidator getUserValidator() {
        return UserValidatorUnit.getInstance();
    }

}
