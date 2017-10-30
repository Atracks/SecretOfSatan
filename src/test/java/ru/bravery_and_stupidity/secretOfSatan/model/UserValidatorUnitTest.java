package ru.bravery_and_stupidity.secretOfSatan.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bravery_and_stupidity.secretOfSatan.TestDataProvider;

final class UserValidatorUnitTest {

    private UserValidator targetOfTesting = null;

    private TestDataProvider testDataProvider = TestDataProvider.INSTANCE;

    @BeforeEach
    void setUp() {
        targetOfTesting = UserValidatorUnit.getInstance();
    }

    @Test
    void userIsOk() {
        User okUser = testDataProvider.getSimpleUserExample();
        boolean isUserWrong = targetOfTesting.isWrong(okUser);
        Assertions.assertFalse(isUserWrong);
    }

    @Test
    void invalidUsername() {
        // denied characters are: "\"\'!@#$%^&*(){}[]\\|/?:;,."
        User invalidUser = testDataProvider.getSimpleUserExample();
        invalidUser.setName("na\"me");
        boolean isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na'me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na;me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na(me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na\\me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na/me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na.me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na,me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na?me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("na:me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setName("");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);
    }

    @Test
    void invalidLogin() {
        // denied characters are: "\"\'!@#$%^&*(){}[]\\|/?:;,."
        User invalidUser = testDataProvider.getSimpleUserExample();
        invalidUser.setLogin("na\"me");
        boolean isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na'me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na;me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na(me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na\\me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na/me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na.me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na,me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na?me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na:me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setLogin("na me");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);
    }

    @Test
    void invalidPassword() {
        User invalidUser = testDataProvider.getSimpleUserExample();

        invalidUser.setPassword("");
        boolean isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setPassword("\"");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setPassword("'");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setPassword(";");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);

        invalidUser.setPassword(":");
        isUserWrong = targetOfTesting.isWrong(invalidUser);
        Assertions.assertTrue(isUserWrong);
    }

}
