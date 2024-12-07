package com.klu.labexam.demo;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import com.klu.labexam.entity.*;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        try {
            insertSessions(session);
            performCriteriaQueries(session);
        } finally {
            session.close();
            factory.close();
        }
    }

    private static void insertSessions(Session session) {
        Transaction transaction = session.beginTransaction();

        Customer c1 = new Customer();
        c1.setName("Navya");
        c1.setEmail("Navya@example.com");
        c1.setAge(30);
        c1.setLocation("New York");

        Customer c2 = new Customer();
        c2.setName("Deepika");
        c2.setEmail("Deepika@example.com");
        c2.setAge(25);
        c2.setLocation("California");

        session.save(c1);
        session.save(c2);

        transaction.commit();
        System.out.println("Records inserted successfully.");
    }

    private static void performCriteriaQueries(Session session) {
        Criteria criteria;

        System.out.println("Customers with age greater than 25:");
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 25));
        List<Customer> result = criteria.list();
        result.forEach(c -> System.out.println(c.getName() + " - " + c.getAge()));

       
       
    }
}
