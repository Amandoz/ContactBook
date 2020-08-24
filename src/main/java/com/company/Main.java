package com.company;

import com.company.Dao.DaoHibernate;
import com.company.Dao.DaoJDBS;
import com.company.Model.Address;
import com.company.Model.Contact;

public class Main {

    public static void main(String[] args) {
        DaoJDBS test = new DaoJDBS();
        DaoHibernate test2 = new DaoHibernate();//здесь открываю подключение

        Address address = new Address("Verhne-Pecherskaya", 7);//задаем адресс
        Address address1 = new Address("Verhne-Pecherskaya", 7);//задаем такой же адресс
        test2.createContactTable();//создаем таблицу
        test2.addContact("Misha1", "Terentev", "Alexeevich", 22, address);//создается запись в таблице адресс с id 1
        test2.addContact("Misha2", "Terentev", "Alexeevich", 22, address1);//создается запись в таблице адресс с id 2
        test2.addContact("Misha3", "Terentev", "Alexeevich", 22, address);//запись указывает на таблицу с id 1 ну тут логично ссылки равны же
        //test2.dropContactTable(); плохо реализовано выдаст исключение удаление не каскадом, но это не к теме

    }
}
