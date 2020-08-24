package com.company.Service;

import com.company.Dao.DaoHibernate;
import com.company.Dao.DaoJDBS;
import com.company.Model.Address;
import com.company.Model.Contact;

import java.util.List;

public class ContactService implements ContactServiceImpl {
    private DaoJDBS daoJDBS = new DaoJDBS();
    private DaoHibernate daoHibernate = new DaoHibernate();
    @Override
    public void addContact(String firstName, String lastName, String middleName, int age, Address address) {
        daoJDBS.addContact(firstName, lastName, middleName, age, address);
    }

    @Override
    public Contact getContactById(long id) {
        return daoJDBS.getContactById(id);
    }

    @Override
    public void createContactTable() {
        daoHibernate.createContactTable();
    }

    @Override
    public void dropContactTable() {
        daoJDBS.dropContactTable();
    }

    @Override
    public void cleanAllContact() {
        daoJDBS.cleanAllContact();
    }

    @Override
    public List<Contact> getAllContact() {
        return daoJDBS.getAllContact();
    }
}
