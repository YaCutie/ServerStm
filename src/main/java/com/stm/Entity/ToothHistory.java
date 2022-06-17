package com.stm.Entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tooth_history")
public class ToothHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "toothnumber", nullable = false)
    private Integer toothnumber;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clientid")
    private Client clientid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "servicesid")
    private Service servicesid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "receptionid")
    private Reception receptionid;

    @Column(name = "datetimeservice", nullable = false)
    private Instant datetimeservice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToothnumber() {
        return toothnumber;
    }

    public void setToothnumber(Integer toothnumber) {
        this.toothnumber = toothnumber;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
    }

    public Service getServicesid() {
        return servicesid;
    }

    public void setServicesid(Service servicesid) {
        this.servicesid = servicesid;
    }

    public Reception getReceptionid() {
        return receptionid;
    }

    public void setReceptionid(Reception receptionid) {
        this.receptionid = receptionid;
    }

    public Instant getDatetimeservice() {
        return datetimeservice;
    }

    public void setDatetimeservice(Instant datetimeservice) {
        this.datetimeservice = datetimeservice;
    }

}