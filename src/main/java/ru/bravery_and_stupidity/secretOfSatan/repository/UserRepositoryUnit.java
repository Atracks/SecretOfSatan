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
    public User getUser(@NotNull String login) {
        User user = em.find(User.class, login);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = em.createQuery("FROM User").getResultList();
        return users;
    }

    @Override
    public void deleteUser(String login) {
        User user = em.find(User.class, login);
        em.remove(user);
    }
}
