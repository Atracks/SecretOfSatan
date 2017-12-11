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
        user.setName("Bubenshikov Alexander");
        user.setLogin("Atracks");
        user.setDesire("I'm a fucking crazy gay. I wont sex drugs and rock'n'roll!!!");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("15320666a");
        user.setPassword(encodedPassword);
        user.setAdmin(true);
        session.persist(user);

        user = new User();
        user.setName("Baryshev Lev");
        user.setLogin("Sugarbaron");
        user.setDesire("I want the Harley Davidson!");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("15320666a");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("Ivanov Andrey");
        user.setLogin("Ammoth");
        user.setDesire("I want new foot!!!");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("15320666a");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("Kozlov Volodya");
        user.setLogin("Real");
        user.setDesire("I want 3 tracks of coca-cola and piece of ananas");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("15320666a");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("Ivanova Ekaterina");
        user.setLogin("Justagirl");
        user.setDesire("I wont 2 box of chocolate and bananas!!");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("15320666a");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("Baryshev Fill");
        user.setLogin("Zoldarius");
        user.setDesire("I want a rock guitar!!");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("15320666a");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        user = new User();
        user.setName("Pozdnykov Volodya");
        user.setLogin("Kawan");
        user.setDesire("I want 3 tracks of coca-cola and pyh pyh");
        passwordEncoder = new BCryptPasswordEncoder();
        encodedPassword = passwordEncoder.encode("15320666a");
        user.setPassword(encodedPassword);
        user.setAdmin(false);
        session.persist(user);

        transaction.commit();
    }
}
