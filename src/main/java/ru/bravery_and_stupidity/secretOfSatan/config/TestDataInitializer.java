package ru.bravery_and_stupidity.secretOfSatan.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

import ru.bravery_and_stupidity.secretOfSatan.model.User;

@Component
public class TestDataInitializer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void init() throws Exception {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setName("username");
        user.setLogin("someLogin");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("somePass1");
        user.setPassword(encodedPassword);
        user.setAdmin(true);
        session.persist(user);

        user = new User();
        user.setName("username2");
        user.setLogin("someLogin2");
        user.setDesire("Big black ass");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("somePass2");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("username3");
        user.setLogin("someLogin3");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("somePass3");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("username4");
        user.setLogin("someLogin4");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("somePass4");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("username5");
        user.setLogin("someLogin5");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("somePass5");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("username6");
        user.setLogin("someLogin6");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("somePass6");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        transaction.commit();
    }
}
