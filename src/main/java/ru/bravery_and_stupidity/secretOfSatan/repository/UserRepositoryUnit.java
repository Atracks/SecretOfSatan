package ru.bravery_and_stupidity.secretOfSatan.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.bravery_and_stupidity.secretOfSatan.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
class UserRepositoryUnit implements UserRepository {

    @PersistenceContext
    private EntityManager storage;

    @Override
    public void saveUser(@NotNull User user) {
        storage.merge(user);
    }

    @Nullable
    @Override
    public User getUser(@NotNull String login) {
        String query = "SELECT user FROM UserUnit user WHERE login = :login";
        return storage.createQuery(query, User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public List<User> getUsers() {
        return storage.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void deleteUser(@NotNull String login) {
        String query = "DELETE FROM UserUnit WHERE login = :login";
        storage.createQuery(query, User.class)
                .executeUpdate();
    }

}
