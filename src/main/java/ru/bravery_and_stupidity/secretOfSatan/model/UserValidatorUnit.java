package ru.bravery_and_stupidity.secretOfSatan.model;


import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UserValidatorUnit implements UserValidator {

    private static UserValidator instance = null;

    private static Logger log = Logger.getLogger(UserValidatorUnit.class);

    public static UserValidator getInstance() {
        if (null == instance) {
            instance = new UserValidatorUnit();
        }
        return instance;
    }

    // singleton: examples constructing is denied
    private UserValidatorUnit() {
    }

    @Override
    public boolean isWrong(@Nullable User user) {
        if (null == user) {
            return true;
        }

        String username = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();

        return (isUsernameWrong(username)
                || isLoginWrong(login)
                || isPasswordWrong(password));
    }

    @Override
    public boolean isWrong(@Nullable String login) {
        return isLoginWrong(login);
    }

    @Override
    public boolean isWrong(@Nullable String login, @Nullable String password) {
        return (isLoginWrong(login) || isPasswordWrong(password));
    }

    private boolean isUsernameWrong(String username) {
        if (null == username) {
            log.info("invalid username (username is null)");
            return true;
        }

        final String allowedNamePatternDescription = "([a-zA-Z ]+|[а-яёА-ЯЁ ]+)";
        Pattern allowedNamePattern = Pattern.compile(allowedNamePatternDescription);
        Matcher matcher = allowedNamePattern.matcher(username);

        if (!matcher.matches()) {
            log.info("invalid username: " + username);
            return true;
        }
        return false;
    }

    private boolean isLoginWrong(String login) {
        if (null == login) {
            log.info("invalid login (login is null)");
            return true;
        }

        final String allowedLoginPatternDescription = "[\\w]+";
        Pattern allowedLoginPattern = Pattern.compile(allowedLoginPatternDescription);
        Matcher matcher = allowedLoginPattern.matcher(login);

        if (!matcher.matches()) {
            log.info("invalid login: " + login);
            return true;
        }
        return false;
    }

    private boolean isPasswordWrong(String password) {
        if (null == password) {
            log.info("invalid password (password is null)");
            return true;
        }

        if (password.isEmpty()) {
            log.info("invalid password (password is empty)");
            return true;
        }

        final String deniedPasswordPatternDescription = "[/\"'\\\\;:]+";
        Pattern deniedPasswordPattern = Pattern.compile(deniedPasswordPatternDescription);
        Matcher matcher = deniedPasswordPattern.matcher(password);

        if (matcher.matches()) {
            log.info("invalid password: " + password);
            return true;
        }
        return false;
    }

}
