package com.stm.Entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "reception_time", nullable = false)
    private Instant receptionTime;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clientid")
    private Client clientid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clinicid")
    private Clinic clinicid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "personalid", nullable = false)
    private Personal personalid;

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

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
    }

    public Clinic getClinicid() {
        return clinicid;
    }

    public void setClinicid(Clinic clinicid) {
        this.clinicid = clinicid;
    }

    public Personal getPersonalid() {
        return personalid;
    }

    public void setPersonalid(Personal personalid) {
        this.personalid = personalid;
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