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

import java.util.ArrayList;
import java.util.List;


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
        String expectedLogin = incomingData.getLogin();
        repositoryMock.getUser(expectedLogin);
        EasyMock.expectLastCall().andReturn(null);

        User expectedUser = incomingData.mapToModel();
        repositoryMock.saveUser(expectedUser);
    }

    @Test
    void addUserCaseInvalidData() {
        UserDao invalidUserData = testDataProvider.getInvalidUserDaoExample();
        try {
            targetOfTesting.addUser(invalidUserData);
            Assertions.fail("an exception must be thrown in case of invalid argument");
        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
    }

    @Test
    void addUserCaseLoginIsNotUnique() {
        UserDao userData = testDataProvider.getSimpleUserDaoExample();
        addUserCaseLoginIsNotUniquePrepareMocks(userData);

        EasyMock.replay(repositoryMock);
        try {
            targetOfTesting.addUser(userData);
            Assertions.fail("an exception must be thrown in case of invalid argument");
        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
        EasyMock.verify(repositoryMock);
    }

    private void addUserCaseLoginIsNotUniquePrepareMocks(UserDao incomingData) {
        String expectedLogin = incomingData.getLogin();
        User someUser = testDataProvider.getSimpleUserExample();

        repositoryMock.getUser(expectedLogin);
        EasyMock.expectLastCall().andReturn(someUser);
    }

    @Test
    void updateUserCaseHappyPath() {
        UserDao simpleUserData = testDataProvider.getSimpleUserDaoExample();
        updateUserCaseHappyPathPrepareMocks(simpleUserData);

        EasyMock.replay(repositoryMock);
        targetOfTesting.updateUser(simpleUserData);
        EasyMock.verify(repositoryMock);
    }

    private void updateUserCaseHappyPathPrepareMocks(UserDao incomingData) {
        String login = incomingData.getLogin();
        User userToUpdate = testDataProvider.getSimpleUserExample();
        repositoryMock.getUser(login);
        EasyMock.expectLastCall().andReturn(userToUpdate);

        User expectedUser = incomingData.mapToModel();
        repositoryMock.saveUser(expectedUser);
    }

    @Test
    void updateUserCaseInvalidData() {
        UserDao invalidUserData = testDataProvider.getInvalidUserDaoExample();
        try {
            targetOfTesting.updateUser(invalidUserData);
            Assertions.fail("an exception must be thrown in case of invalid argument");
        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
    }

    @Test
    void updateUserCaseForeignLogin() {
        UserDao incomingUserData = testDataProvider.getSimpleUserDaoExample();
        updateUserCaseForeignLoginPrepareMocks(incomingUserData);

        EasyMock.replay(repositoryMock);
        try {
            targetOfTesting.updateUser(incomingUserData);
            Assertions.fail("an exception must be thrown in case of invalid argument");
        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
        EasyMock.verify(repositoryMock);
    }

    private void updateUserCaseForeignLoginPrepareMocks(UserDao incomingUserData) {
        String login = incomingUserData.getLogin();
        repositoryMock.getUser(login);
        EasyMock.expectLastCall().andReturn(null);
    }

    @Test
    void getUserCaseHappyPath() {
        String login = "someValidLogin";
        UserDao expectedUserData = getUserCaseHappyPathPrepareMocks(login);
        EasyMock.replay(repositoryMock);

        UserDao actualUserData = targetOfTesting.getUser(login);

        Assertions.assertNotNull(actualUserData);
        Assertions.assertEquals(expectedUserData, actualUserData);
        EasyMock.verify(repositoryMock);
    }

    private UserDao getUserCaseHappyPathPrepareMocks(String login) {
        User requiredUser = testDataProvider.getSimpleUserExample();
        requiredUser.setLogin(login);

        repositoryMock.getUser(login);
        EasyMock.expectLastCall().andReturn(requiredUser);

        return requiredUser.mapToDao();
    }

    @Test
    void getUserCaseNoSuchUser() {
        String login = "someValidLogin";
        getUserCaseNoSuchUserPrepareMocks(login);
        EasyMock.replay(repositoryMock);

        UserDao actualUserData = targetOfTesting.getUser(login);

        Assertions.assertNull(actualUserData);
        EasyMock.verify(repositoryMock);
    }

    private void getUserCaseNoSuchUserPrepareMocks(String login) {
        repositoryMock.getUser(login);
        EasyMock.expectLastCall().andReturn(null);
    }

    @Test
    void getUserCaseInvalidLogin() {
        String invalidLogin = ";DROP TABLE users;";
        try {
            targetOfTesting.getUser(invalidLogin);
            Assertions.fail("an exception must be thrown in case of invalid argument");
        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
    }

    @Test
    void getUsersCaseHappyPath() {
        List<UserDao> expected = getUsersCaseHappyPathPrepareMocks();

        EasyMock.replay(repositoryMock);
        List<UserDao> actual = targetOfTesting.getUsers();
        EasyMock.verify(repositoryMock);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            UserDao eachExpected = expected.get(i);
            UserDao eachActual = actual.get(i);
            Assertions.assertEquals(eachExpected, eachActual);
        }
    }

    private List<UserDao> getUsersCaseHappyPathPrepareMocks() {
        List<User> users = testDataProvider.getSimpleListOfUsers();
        repositoryMock.getUsers();
        EasyMock.expectLastCall().andReturn(users);

        List<UserDao> usersData = new ArrayList<>();
        for (User user : users) {
            usersData.add(user.mapToDao());
        }
        return usersData;
    }

    @Test
    void deleteUserCaseHappyPath() {
        String login = "someValidLoginExample";
        repositoryMock.deleteUser(login);

        EasyMock.replay(repositoryMock);
        targetOfTesting.deleteUser(login);
        EasyMock.verify(repositoryMock);
    }

    @Test
    void deleteUserCaseInvalidLogin() {
        String invalidLogin = "DROP TABLE users";
        try {
            targetOfTesting.deleteUser(invalidLogin);
            Assertions.fail("an exception must be thrown in case of invalid argument");
        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
    }

    /*@Test
    void calculateTargets() {
    }*/

}
