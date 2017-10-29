package ru.bravery_and_stupidity.secretOfSatan.services;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bravery_and_stupidity.secretOfSatan.TestDataProvider;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.model.UserValidator;
import ru.bravery_and_stupidity.secretOfSatan.repository.UserRepository;


final class UserServiceUnitTest {

    private UserServiceUnit targetOfTesting;

    private final UserRepository repositoryMock = EasyMock.createMock(UserRepository.class);

    private TestDataProvider testDataProvider = TestDataProvider.INSTANCE;

    @BeforeEach
    void setUp() {
        EasyMock.reset(repositoryMock);
        UserValidator validator = testDataProvider.getUserValidator();
        targetOfTesting = new UserServiceUnit(repositoryMock, validator);
    }

    @Test
    void addUserCaseHappyPath() {
        UserDao simpleUserData = testDataProvider.getSimpleUserDaoExample();
        addUserCaseHappyPathPrepareMocks(simpleUserData);

        EasyMock.replay(repositoryMock);
        targetOfTesting.addUser(simpleUserData);
        EasyMock.verify(repositoryMock);

    }

    private void addUserCaseHappyPathPrepareMocks(UserDao incomingData) {
        User expectedUser = incomingData.mapToModel();
        repositoryMock.saveUser(expectedUser);
    }

    @Test
    void addUserCaseInvalidUser() {
        UserDao invalidUserData = testDataProvider.getInvalidUserDaoExample();
        try {
            targetOfTesting.addUser(invalidUserData);
            Assertions.fail("an exception must be thrown in case of invalid argument");
        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
    }

    /*@Test
    void updateUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void calculateTargets() {
    }*/

}
