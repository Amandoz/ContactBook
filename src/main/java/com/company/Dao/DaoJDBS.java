package com.company.Dao;

import com.company.Model.Address;
import com.company.Model.Contact;
import com.company.Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DaoJDBS extends Util implements DaoImpl {


    @Override
    public void addContact(String firstName, String lastName, String middleName, int age, Address address) {
        String addressSQL = "INSERT INTO address (street, home_number) VALUE (?, ?);";
        String contactSQL = "INSERT INTO contacts (first_name, last_name, middle_name, age, address_id) VALUE (?, ?, ?, ?, ?);";

        try (Connection connection = getConnection()) {
            if (address != null) {
                try (PreparedStatement statement1 = connection.prepareStatement(addressSQL, Statement.RETURN_GENERATED_KEYS);
                     PreparedStatement statement2 = connection.prepareStatement(contactSQL);) {
                    statement1.setString(1, address.getStreet());
                    statement1.setInt(2, address.getHomeNumber());
                    statement1.executeUpdate();
                    statement2.setString(1, firstName);
                    statement2.setString(2, lastName);
                    statement2.setString(3, middleName);
                    statement2.setInt(4, age);
                    ResultSet resultSet = statement1.getGeneratedKeys();
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        statement2.setInt(5, id);
                    }
                    statement2.executeUpdate();
                    System.out.println("Users " + firstName + " was added!");
                }
            } else {
                String contactSQL2 = "INSERT INTO contacts (first_name, last_name, middle_name, age) VALUE (?, ?, ?, ?);";
                try (PreparedStatement statement1 = connection.prepareStatement(contactSQL2)) {
                    statement1.setString(1, firstName);
                    statement1.setString(2, lastName);
                    statement1.setString(3, middleName);
                    statement1.setInt(4, age);
                    statement1.executeUpdate();
                    System.out.println("Users " + firstName + " was added!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error add contact");
            e.printStackTrace();
        }
    }

    @Override
    public Contact getContactById(long id) {
        return  null;
    }

    @Override
    public void createContactTable() {
        String createAddress = "CREATE TABLE IF NOT EXISTS`address`(`id` bigint NOT NULL AUTO_INCREMENT,\n" +
                "  `street` varchar(45) NOT NULL,\n" +
                "  `home_number` smallint NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ")";
        String createContacts = "CREATE TABLE IF NOT EXISTS `contacts` (\n" +
                "  `id` bigint NOT NULL AUTO_INCREMENT,\n" +
                "  `first_name` varchar(15) NOT NULL,\n" +
                "  `last_name` varchar(20) NOT NULL,\n" +
                "  `middle_name` varchar(20) DEFAULT NULL,\n" +
                "  `age` SMALLINT DEFAULT NULL,\n" +
                "  `address_id` bigint DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `id_UNIQUE` (`id`),\n" +
                "  KEY `address_fk_idx` (`address_id`),\n" +
                "  CONSTRAINT `address_fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE\n" +
                ")";
        try (Connection connection = getConnection();
             PreparedStatement statement1 = connection.prepareStatement(createAddress);
             PreparedStatement statement2 = connection.prepareStatement(createContacts)) {
            statement1.executeUpdate();
            System.out.println("Table address create");
            statement2.executeUpdate();
            System.out.println("Table contacts create");
        } catch (Exception e) {
            System.out.println("Error create tables");
            e.printStackTrace();
        }
    }

    @Override
    public void dropContactTable() {
        String dropAddress = "DROP TABLE IF EXISTS address";
        String dropContacts = "DROP TABLE IF EXISTS contacts";

        try (Connection connection = getConnection();
             PreparedStatement statement1 = connection.prepareStatement(dropAddress);
             PreparedStatement statement2 = connection.prepareStatement(dropContacts)) {
            statement2.executeUpdate();
            System.out.println("Success drop contacts");
            statement1.executeUpdate();
            System.out.println("Success drop address");
        } catch (Exception e) {
            System.out.println("Error drop tables");
            e.printStackTrace();
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
