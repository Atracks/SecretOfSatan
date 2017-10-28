package ru.bravery_and_stupidity.secretOfSatan.model;


import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "users")
@IdClass(UserAuthenticationData.class)
public class UserUnit implements User {

    private static final int NOT_INITIALIZED = -1;

    @NotNull
    @Basic
    @Column(name = "name")
    private String name = "";

    @Id
    @AttributeOverrides({
        @AttributeOverride(name = "login", column = @Column(name="login")),
        @AttributeOverride(name = "password", column = @Column(name="password"))
    })

    @NotNull
    @Basic
    private String login = "";

    @NotNull
    @Basic
    private String password = "";

    @NotNull
    @Basic
    @Column(name = "desire")
    private String desire = "";

    @Basic
    @Column(name = "isAdmin")
    private boolean isAdmin = false;

    @Basic
    @Column(name = "target")
    private int target = NOT_INITIALIZED;

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
    public void setTarget(int target) {
        this.target = target;
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

    @Override
    public int getTarget() {
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
        return  (that.isAdmin == this.isAdmin)
                && (that.target == this.target)
                && (that.name.equals(this.name))
                && (that.login.equals(this.login))
                && (that.password.equals(this.password))
                && (that.desire.equals(this.desire));
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + desire.hashCode();
        result = 31 * result + (isAdmin ? 1 : 0);
        result = 31 * result + target;
        return result;
    }

    @Override
    public String toString() {
        return String.format("User{name = %s, login = %s, password = %s, desire = %s, isAdmin = %s, target = %d}",
                 name, login, password, desire, isAdmin, target);
    }

}
