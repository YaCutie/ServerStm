package com.stm.Entity;

import javax.persistence.*;

@Entity
@Table(name = "specializations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "specializationname", nullable = false)
    private String specializationname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecializationname() {
        return specializationname;
    }

    public void setSpecializationname(String specializationname) {
        this.specializationname = specializationname;
    }

}