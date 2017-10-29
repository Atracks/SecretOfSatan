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

    private TestDataProvider testDataProvider = TestDataProvider.INSTANCE;

    @BeforeEach
    void setUp() {
        EasyMock.reset(repositoryMock);
        targetOfTesting = new UserServiceUnit(repositoryMock);
    }

    @Test
    void addUserCaseHappyPath() {
        UserDao simpleUserDao = testDataProvider.getSimpleUserDaoExample();
        addUserCaseHappyPathPrepareMocks(simpleUserDao);

        EasyMock.replay(repositoryMock);
        targetOfTesting.addUser(simpleUserDao);
        EasyMock.verify(repositoryMock);

    }

    private void addUserCaseHappyPathPrepareMocks(UserDao incomingData) {
        User expectedUser = incomingData.mapToModel();
        repositoryMock.saveUser(expectedUser);
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
