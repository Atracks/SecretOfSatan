package ru.bravery_and_stupidity.secretOfSatan.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        if(!isUserExist(user.getLogin())) {
            encodeUserPassword(user);
        }
        storage.merge(user);
    }

    private boolean isUserExist(@NotNull String login) {
        return getUser(login) != null;
    }

    private void encodeUserPassword(@NotNull User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Nullable
    @Override
    public User getUser(@NotNull String login) {
        return storage.find(User.class, login);
    }

    @Override
    public List<User> getUsers() {
        return storage.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void deleteUser(@NotNull String login) {
        String query = "DELETE FROM User WHERE login = :login";
        storage.createQuery(query)
                .setParameter("login", login)
                .executeUpdate();
    }

}
