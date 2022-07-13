package com.stm.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    public Appointment(String receptionTime, Client clientid, Clinic clinicid, Personal personalid, Integer cabinetNumber, Statute status) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(receptionTime);
        this.receptionTime = date.toInstant();
        this.clientId = clientid;
        this.clinicId = clinicid;
        this.personalId = personalid;
        this.cabinetNumber = cabinetNumber;
        this.status = status;
    }
    public Appointment() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "reception_time", nullable = false)
    private Instant receptionTime;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clientid")
    private Client clientId;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clinicid")
    private Clinic clinicId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "personalid", nullable = false)
    private Personal personalId;

    @Column(name = "cabinet_number", nullable = false)
    private Integer cabinetNumber;

    @ManyToOne(fetch = FetchType. EAGER, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private Statute status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getReceptionTime() {
        return receptionTime;
    }

    public void setReceptionTime(Instant receptionTime) {
        this.receptionTime = receptionTime;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientid) {
        this.clientId = clientid;
    }

    public Clinic getClinicId() {
        return clinicId;
    }

    public void setClinicId(Clinic clinicid) {
        this.clinicId = clinicid;
    }

    public Personal getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Personal personalid) {
        this.personalId = personalid;
    }

    public Integer getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(Integer cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public Statute getStatus() {
        return status;
    }

    public void setStatus(Statute status) {
        this.status = status;
    }

}