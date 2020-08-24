package com.company.Dao;

import com.company.Model.Address;
import com.company.Model.Contact;

import java.util.List;

public interface DaoImpl {

    void addContact(String firstName, String lastName, String middleName, int age, Address address);

    Contact getContactById(long id);

    void createContactTable();

    void dropContactTable();

    void cleanAllContact();

    List<Contact> getAllContact();
}
