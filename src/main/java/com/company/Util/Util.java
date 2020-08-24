package com.company.Util;


import com.company.Model.Address;
import com.company.Model.Contact;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    private final static String USER = "root";
    private final static String PASS = "admin";
    private final static String URL = "jdbc:mysql://localhost:3306/contact_book?serverTimezone=UTC";

    public Connection getConnection() {
        Connection connection = null;
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Success connection!");
            } catch (Exception e) {
                System.out.println("Error connection!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Contact.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            System.out.println("Success hibernate");
        }
        return sessionFactory;
    }

}
