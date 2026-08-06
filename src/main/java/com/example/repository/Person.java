package com.example.repository;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="Persons")
public class Person implements Serializable {
    @Id
    @Column(name="PersonID")
    private int personId;

    @Column(name="lastname")
    private String lastName;

    @Column(name="firstname")
    private String firstname;

    @Column(name="Address")
    private String address;

    @Column(name="City")
    private String city;


    @OneToOne
    @JoinColumn(
            name = "occupation_id",
            referencedColumnName = "occ_id"
    )
    private Occupations occupations;

    public Occupations getOccupations() {
        return occupations;
    }

    public void setOccupations(Occupations occupations) {
        this.occupations = occupations;
    }


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
