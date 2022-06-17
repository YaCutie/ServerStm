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
    private Personal personalid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "scheduleid")
    private Appointment scheduleid;

    @Column(name = "datetimeservice", nullable = false)
    private Instant datetimeservice;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clientid")
    private Client clientid;

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

    public Personal getPersonalid() {
        return personalid;
    }

    public void setPersonalid(Personal personalid) {
        this.personalid = personalid;
    }

    public Appointment getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Appointment scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Instant getDatetimeservice() {
        return datetimeservice;
    }

    public void setDatetimeservice(Instant datetimeservice) {
        this.datetimeservice = datetimeservice;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
    }

}