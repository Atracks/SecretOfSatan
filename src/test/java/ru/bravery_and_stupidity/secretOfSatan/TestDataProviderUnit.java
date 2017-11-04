package ru.bravery_and_stupidity.secretOfSatan;

import org.jetbrains.annotations.NotNull;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidator;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidatorUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public List<User> getSimpleListOfUsers() {
        User user1 = new User();
        user1.setLogin("user1Login");
        user1.setName("user1");
        user1.setPassword("user1Password");
        user1.setTarget("user2");
        user1.setAdmin(false);

        User user2 = new User();
        user2.setLogin("user2Login");
        user2.setName("user2");
        user2.setPassword("user2Password");
        user2.setTarget("user3");
        user2.setAdmin(false);

        User user3 = new User();
        user3.setLogin("user3Login");
        user3.setName("user3");
        user3.setPassword("user3Password");
        user3.setTarget("");
        user3.setAdmin(false);

        User user4 = new User();
        user4.setLogin("user4Login");
        user4.setName("user4");
        user4.setPassword("user4Password");
        user4.setTarget("");
        user4.setAdmin(false);

        User user5 = new User();
        user5.setLogin("user5Login");
        user5.setName("user5");
        user5.setPassword("user5Password");
        user5.setTarget("");
        user5.setAdmin(false);

        List<User> users = new ArrayList<>(5);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        return Collections.unmodifiableList(users);
    }

    @NotNull
    @Override
    public UserValidator getUserValidator() {
        return UserValidatorUnit.getInstance();
    }

}
