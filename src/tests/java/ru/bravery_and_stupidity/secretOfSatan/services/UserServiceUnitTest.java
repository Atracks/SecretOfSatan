package ru.bravery_and_stupidity.secretOfSatan.services;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bravery_and_stupidity.secretOfSatan.TestDataProvider;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;
import ru.bravery_and_stupidity.secretOfSatan.model.User;
import ru.bravery_and_stupidity.secretOfSatan.repository.UserRepository;


final class UserServiceUnitTest {

    private UserServiceUnit targetOfTesting;

    private final UserRepository repositoryMock = EasyMock.createMock(UserRepository.class);

    private TestDataProvider testData = TestDataProvider.INSTANCE;

    @BeforeEach
    private void setUp() {
        EasyMock.reset(repositoryMock);
        targetOfTesting = new UserServiceUnit(repositoryMock);
    }

    /*@Test
    private void addUserCaseHappyPath() {
        addUserCaseHappyPathPrepareMocks();

    }*/

    private void addUserCaseHappyPathPrepareMocks() {
        User simpleUser = testData.getNewSimpleUserExample();
        repositoryMock.saveUser(simpleUser);
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
