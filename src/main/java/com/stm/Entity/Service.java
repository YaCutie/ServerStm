package com.stm.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
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
    private Material materialId;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "clinicid")
    private Clinic clinicId;

    public Service() {

    }

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

    public Material getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Material materialid) {
        this.materialId = materialid;
    }

    public Clinic getClinicId() {
        return clinicId;
    }

    public void setClinicId(Clinic clinicid) {
        this.clinicId = clinicid;
    }

}