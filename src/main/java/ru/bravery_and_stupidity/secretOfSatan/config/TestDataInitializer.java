package ru.bravery_and_stupidity.secretOfSatan.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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
        user.setPassword("somePass");

        session.persist(user);
        transaction.commit();
    }

}
