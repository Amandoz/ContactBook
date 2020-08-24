package com.company.Dao;

import com.company.Model.Address;
import com.company.Model.Contact;
import com.company.Util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DaoHibernate extends Util implements DaoImpl {

    @Override
    public void addContact(String firstName, String lastName, String middleName, int age, Address address) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new Contact(firstName, lastName, middleName, age, address));
            transaction.commit();
        }
    }

    @Override
    public Contact getContactById(long id) {
        return null;
    }

    @Override
    public void createContactTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS address (" +
                    "id bigint NOT NULL AUTO_INCREMENT," +
                    "street varchar(30) NOT NULL," +
                    "home_number int NOT NULL, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
        }
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS contacts (" +
                    "id bigint NOT NULL AUTO_INCREMENT," +
                    "first_name varchar(15) NOT NULL," +
                    "last_name varchar(25) NOT NULL, " +
                    "middle_name varchar(25), " +
                    "age int NOT NULL ," +
                    "address_id bigint , " +
                    "PRIMARY KEY (id), " +
                    "FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE ON UPDATE CASCADE)").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropContactTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS contacts").executeUpdate();
            transaction.commit();
        }
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS address").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void cleanAllContact() {

    }

    @Override
    public List<Contact> getAllContact() {
        return null;
    }
}
