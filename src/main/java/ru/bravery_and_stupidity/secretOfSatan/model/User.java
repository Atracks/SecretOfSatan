package ru.bravery_and_stupidity.secretOfSatan.model;


import org.jetbrains.annotations.NotNull;
import ru.bravery_and_stupidity.secretOfSatan.dao.UserDao;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private static final String NOT_INITIALIZED = "";

    @NotNull
    @Id
    @Basic
    @Column(name = "login")
    private String login = NOT_INITIALIZED;

    @NotNull
    @Basic
    @Column(name = "password")
    private String password = NOT_INITIALIZED;

    @NotNull
    @Basic
    @Column(name = "name")
    private String name = NOT_INITIALIZED;

    @NotNull
    @Basic
    @Column(name = "desire")
    private String desire = NOT_INITIALIZED;

    @Basic
    @Column(name = "isAdmin")
    private boolean isAdmin = false;

    @Basic
    @Column(name = "target")
    private String target = NOT_INITIALIZED;

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public void setDesire(@NotNull String desire) {
        this.desire = desire;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setTarget(@NotNull String targetLogin) {
        this.target = targetLogin;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getDesire() {
        return desire;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @NotNull
    public String getTarget() {
        return target;
    }

    @NotNull
    public UserDao mapToDao() {
        UserDao dao = new UserDao();
        dao.setLogin(login);
        dao.setPassword(password);
        dao.setName(name);
        dao.setDesire(desire);
        dao.setTarget(target);
        return dao;
    }

    // TODO: replace constructor with instantiation with builder

    @Override
    public boolean equals(Object entity) {
        if (this == entity) {
            return true;
        }
        if (null == entity) {
            return false;
        }
        if (entity.getClass() != this.getClass()) {
            return false;
        }

        User that = (User) entity;
        return  (that.login.equals(this.login))
                && (that.isAdmin == this.isAdmin)
                && (that.target.equals(this.target))
                && (that.name.equals(this.name))
                && (that.password.equals(this.password))
                && (that.desire.equals(this.desire));
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + desire.hashCode();
        result = 31 * result + target.hashCode();
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("User{name = %s, login = %s, password = %s, desire = %s, isAdmin = %s, target = %s}",
                 name, login, password, desire, isAdmin, target);
    }

}
