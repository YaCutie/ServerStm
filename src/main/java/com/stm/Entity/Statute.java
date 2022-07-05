package com.stm.Entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "statutes")
public class Statute {
    public Statute(Integer id, String statusname) {
        this.id = id;
        this.statusname = statusname;
    }

    public Statute() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "statusname", nullable = false)
    private String statusname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

}