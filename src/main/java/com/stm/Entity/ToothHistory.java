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
    private Integer toothNumber;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clientid")
    private Client clientId;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "servicesid")
    private Service servicesId;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "receptionid")
    private Reception receptionId;

    @Column(name = "datetimeservice", nullable = false)
    private Instant datetimeService;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToothNumber() {
        return toothNumber;
    }

    public void setToothNumber(Integer toothnumber) {
        this.toothNumber = toothnumber;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientid) {
        this.clientId = clientid;
    }

    public Service getServicesId() {
        return servicesId;
    }

    public void setServicesId(Service servicesid) {
        this.servicesId = servicesid;
    }

    public Reception getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Reception receptionid) {
        this.receptionId = receptionid;
    }

    public Instant getDatetimeService() {
        return datetimeService;
    }

    public void setDatetimeService(Instant datetimeservice) {
        this.datetimeService = datetimeservice;
    }

}