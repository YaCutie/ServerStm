package com.stm.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "reception")
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "complaints")
    private String complaints;


    @Column(name = "external_inspection_data")
    private String externalInspectionData;


    @Column(name = "treatment_plan")
    private String treatmentPlan;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "personalid")
    private Personal personalId;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "scheduleid")
    private Appointment scheduleId;

    @Column(name = "datetimeservice", nullable = false)
    private Instant datetimeService;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clientid")
    private Client clientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getExternalInspectionData() {
        return externalInspectionData;
    }

    public void setExternalInspectionData(String externalInspectionData) {
        this.externalInspectionData = externalInspectionData;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public Personal getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Personal personalid) {
        this.personalId = personalid;
    }

    public Appointment getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Appointment scheduleid) {
        this.scheduleId = scheduleid;
    }

    public Instant getDatetimeService() {
        return datetimeService;
    }

    public void setDatetimeService(Instant datetimeservice) {
        this.datetimeService = datetimeservice;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientid) {
        this.clientId = clientid;
    }

}