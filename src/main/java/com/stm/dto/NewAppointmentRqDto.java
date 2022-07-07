package com.stm.dto;

import com.stm.Entity.Client;
import com.stm.Entity.Clinic;
import com.stm.Entity.Personal;
import com.stm.Entity.Statute;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.Instant;

@Builder
@AllArgsConstructor
public class NewAppointmentRqDto {
    private String receptionTime;
    private Integer clientid;
    private Integer clinicid;
    private Integer personalid;
    private Integer cabinetNumber;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(Integer cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public Integer getPersonalid() {
        return personalid;
    }

    public void setPersonalid(Integer personalid) {
        this.personalid = personalid;
    }

    public Integer getClinicid() {
        return clinicid;
    }

    public void setClinicid(Integer clinicid) {
        this.clinicid = clinicid;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getReceptionTime() {
        return receptionTime;
    }

    public void setReceptionTime(String receptionTime) {
        this.receptionTime = receptionTime;
    }
}
