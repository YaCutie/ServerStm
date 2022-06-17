package com.stm.Entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "number_contract")
    private String numberContract;

    @Column(name = "date_contract", nullable = false)
    private Instant dateContract;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clientid")
    private Client clientid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "personalid")
    private Personal personalid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberContract() {
        return numberContract;
    }

    public void setNumberContract(String numberContract) {
        this.numberContract = numberContract;
    }

    public Instant getDateContract() {
        return dateContract;
    }

    public void setDateContract(Instant dateContract) {
        this.dateContract = dateContract;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
    }

    public Personal getPersonalid() {
        return personalid;
    }

    public void setPersonalid(Personal personalid) {
        this.personalid = personalid;
    }

}