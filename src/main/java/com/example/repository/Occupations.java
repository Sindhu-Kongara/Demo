package com.example.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Occupations")
public class Occupations {
    @Id
    @Column(name="occ_id")
    private int occ_Id;

    @Column(name="occ_type")
    private String occ_Type;

    @Column(name="occ_name")
    private String occ_Name;

    public int getOcc_Id() {
        return occ_Id;
    }

    public void setOcc_Id(int occ_Id) {
        this.occ_Id = occ_Id;
    }

    public String getOcc_Type() {
        return occ_Type;
    }

    public void setOcc_Type(String occ_Type) {
        this.occ_Type = occ_Type;
    }

    public String getOcc_Name() {
        return occ_Name;
    }

    public void setOcc_Name(String occ_Name) {
        this.occ_Name = occ_Name;
    }


}
