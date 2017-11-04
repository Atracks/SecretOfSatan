package ru.bravery_and_stupidity.secretOfSatan;

import org.jetbrains.annotations.NotNull;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidator;

import java.util.List;

public interface TestDataProvider {

    TestDataProvider INSTANCE = new TestDataProviderUnit();

    @NotNull
    UserDao getSimpleUserDaoExample();

    @NotNull
    UserDao getInvalidUserDaoExample();

    @NotNull
    User getSimpleUserExample();

    @NotNull
    List<User> getSimpleListOfUsers();

    @NotNull
    UserValidator getUserValidator();

}
