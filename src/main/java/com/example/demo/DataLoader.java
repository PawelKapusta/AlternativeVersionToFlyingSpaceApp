package com.example.demo;

import com.example.demo.models.Tourist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(new Tourist("Adam", "Nowak", "Polska"));
        session.save(new Tourist("Tomasz", "Kowalski", "Austria"));
        session.getTransaction().commit();
        session.close();
    }
}
