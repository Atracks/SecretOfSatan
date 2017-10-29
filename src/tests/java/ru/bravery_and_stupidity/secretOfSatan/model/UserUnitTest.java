package ru.bravery_and_stupidity.secretOfSatan.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.bravery_and_stupidity.secretOfSatan.TestDataProvider;


class UserUnitTest {

    private TestDataProvider testDataProvider = TestDataProvider.INSTANCE;

    @Test
    void equals_reflexivity() {
        User targetOfTesting = testDataProvider.getNewSimpleUserExample();
        Assertions.assertTrue(targetOfTesting.equals(targetOfTesting));
    }

    @Test
    void equals_symmetry() {
        User left = testDataProvider.getNewSimpleUserExample();
        User right = testDataProvider.getNewSimpleUserExample();

        Assertions.assertTrue(left.equals(right));
        Assertions.assertTrue(right.equals(left));
    }

    @Test
    void equals_transitivity() {
        User left = testDataProvider.getNewSimpleUserExample();
        User middle = testDataProvider.getNewSimpleUserExample();
        User right = testDataProvider.getNewSimpleUserExample();

        Assertions.assertTrue(left.equals(middle));
        Assertions.assertTrue(middle.equals(right));
        Assertions.assertTrue(left.equals(right));
    }

    @Test
    void equals_consistency() {
        User left = testDataProvider.getNewSimpleUserExample();
        User right = testDataProvider.getNewSimpleUserExample();

        Assertions.assertTrue(left.equals(right));
        Assertions.assertTrue(left.equals(right));
        Assertions.assertTrue(left.equals(right));
        Assertions.assertTrue(left.equals(right));
        Assertions.assertTrue(left.equals(right));
    }

    @Test
    void equals_inequality() {
        User one = testDataProvider.getNewSimpleUserExample();
        User another = testDataProvider.getNewSimpleUserExample();
        another.setName("Another");

        Assertions.assertFalse(one.equals(another));
    }

    @Test
    void setNullName() {
        User rabbit = testDataProvider.getNewSimpleUserExample();
        try {
            rabbit.setName(null);
            Assertions.fail("an exception must be thrown in case of null argument");

        } catch (IllegalArgumentException expectedException) {
            // correct work case
        }
    }

}
