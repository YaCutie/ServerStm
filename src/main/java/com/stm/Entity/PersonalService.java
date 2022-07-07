package com.stm.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "personal_services")
public class PersonalService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    public PersonalService() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}