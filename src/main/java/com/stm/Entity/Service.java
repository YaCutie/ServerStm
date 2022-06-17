package com.stm.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "services_name")
    private String servicesName;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "materialid")
    private Material materialid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clinicid")
    private Clinic clinicid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Material getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Material materialid) {
        this.materialid = materialid;
    }

    public Clinic getClinicid() {
        return clinicid;
    }

    public void setClinicid(Clinic clinicid) {
        this.clinicid = clinicid;
    }

}