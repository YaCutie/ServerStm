package com.stm.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    
    public Client(String surname, String name, String middlename, LocalDate dateOfBirth, String phone, String email, String login, String password){
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Client() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

     
    @Column(name = "surname", nullable = false)
    private String surname;

     
    @Column(name = "name", nullable = false)
    private String name;

     
    @Column(name = "middlename")
    private String middlename;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

     
    @Column(name = "phone", nullable = false)
    private String phone;

     
    @Column(name = "medical_history")
    private String medicalHistory;

     
    @Column(name = "addres")
    private String addres;

     
    @Column(name = "pasport")
    private String pasport;

     
    @Column(name = "login", nullable = false)
    private String login;

     
    @Column(name = "password", nullable = false)
    private String password;

     
    @Column(name = "email", nullable = false)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}