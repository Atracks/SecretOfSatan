package ru.bravery_and_stupidity.secretOfSatan.model;


import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserUnit implements User {

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

    @Override
    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    @Override
    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @Override
    public void setDesire(@NotNull String desire) {
        this.desire = desire;
    }

    @Override
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public void setTarget(@NotNull String targetLogin) {
        this.target = targetLogin;
    }

    @Override
    @NotNull
    public String getName() {
        return name;
    }

    @Override
    @NotNull
    public String getLogin() {
        return login;
    }

    @Override
    @NotNull
    public String getPassword() {
        return password;
    }

    @Override
    @NotNull
    public String getDesire() {
        return desire;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @NotNull
    @Override
    public String getTarget() {
        return target;
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

        UserUnit that = (UserUnit) entity;
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
