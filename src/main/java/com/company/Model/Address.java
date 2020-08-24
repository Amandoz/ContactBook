package com.company.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "street")
    private String street;
    @Column(name = "home_number")
    private int homeNumber;

    public Address() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return  homeNumber == address.homeNumber &&
                Objects.equals(street, address.street);
    } //пробовал реализовать equals со всеми полями то же не сработало

    @Override
    public int hashCode() {
        return Objects.hash(street, homeNumber);
    }

    public Address(String street, int homeNumber) {
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
