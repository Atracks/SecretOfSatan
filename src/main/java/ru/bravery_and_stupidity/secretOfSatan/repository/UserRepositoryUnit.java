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
    private EntityManager em;

    @Override
    public void saveUser(@NotNull User user) {
        em.merge(user);
    }

    @Nullable
    @Override
    public User getUser(@NotNull String login, @NotNull String password) {
        List<User> users = em.createQuery("SELECT user FROM UserUnit user WHERE login = :login " +
          "AND password = :password ")
          .setParameter("login", login)
          .setParameter("password", password).getResultList();
        return users.get(0);
    }

    @Override
    public List<User> getUsers() {
        List<User> users = em.createQuery("FROM User").getResultList();
        return users;
    }

    @Override
    public void deleteUser(int userId) {
        User user = em.find(User.class, userId);
        em.remove(user);
    }
}
