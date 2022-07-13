package com.stm.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "doctorsschedule")
public class Doctorsschedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType. EAGER, optional = false)
    @JoinColumn(name = "clinicid", nullable = false)
    private Clinic clinicId;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "personid")
    private Personal personId;

    @Column(name = "cabinet")
    private String cabinet;

    @Column(name = "starttime")
    private LocalTime startTime;

    @Column(name = "stoptime")
    private LocalTime stopTime;

    @Column(name = "durationofreception")
    private LocalTime durationOfReception;

    @Column(name = "day")
    private Integer day;

    public Doctorsschedule() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clinic getClinicId() {
        return clinicId;
    }

    public void setClinicId(Clinic clinicid) {
        this.clinicId = clinicid;
    }

    public Personal getPersonId() {
        return personId;
    }

    public void setPersonId(Personal personid) {
        this.personId = personid;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime starttime) {
        this.startTime = starttime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stoptime) {
        this.stopTime = stoptime;
    }

    public LocalTime getDurationOfReception() {
        return durationOfReception;
    }

    public void setDurationOfReception(LocalTime durationofreception) {
        this.durationOfReception = durationofreception;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}