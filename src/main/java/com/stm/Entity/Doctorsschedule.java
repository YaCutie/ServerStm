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
    private Clinic clinicid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "personid")
    private Personal personid;


    @Column(name = "cabinet")
    private String cabinet;

    @Column(name = "starttime")
    private LocalTime starttime;

    @Column(name = "stoptime")
    private LocalTime stoptime;

    @Column(name = "durationofreception")
    private LocalTime durationofreception;

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

    public Clinic getClinicid() {
        return clinicid;
    }

    public void setClinicid(Clinic clinicid) {
        this.clinicid = clinicid;
    }

    public Personal getPersonid() {
        return personid;
    }

    public void setPersonid(Personal personid) {
        this.personid = personid;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public LocalTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public LocalTime getStoptime() {
        return stoptime;
    }

    public void setStoptime(LocalTime stoptime) {
        this.stoptime = stoptime;
    }

    public LocalTime getDurationofreception() {
        return durationofreception;
    }

    public void setDurationofreception(LocalTime durationofreception) {
        this.durationofreception = durationofreception;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}