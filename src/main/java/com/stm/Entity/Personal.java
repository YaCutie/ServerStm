package com.stm.Entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "personal")
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "surname")
    private String surname;


    @Column(name = "name")
    private String name;


    @Column(name = "middlename")
    private String middlename;

    @Column(name = "date_of_birth", nullable = false)
    private Instant dateOfBirth;

    @ManyToOne(fetch = FetchType. EAGER, optional = false)
    @JoinColumn(name = "clinicid", nullable = false)
    private Clinic clinicid;

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name = "postid")
    private Post postid;


    @Column(name = "pasport")
    private String pasport;


    @Column(name = "phone")
    private String phone;


    @Column(name = "addres")
    private String addres;


    @Column(name = "login")
    private String login;


    @Column(name = "password")
    private String password;

    @Column(name = "experience")
    private LocalDate experience;


    @Column(name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType. EAGER, optional = false)
    @JoinColumn(name = "specializationid", nullable = false)
    private Specialization specializationid;


    @Column(name = "category")
    private String category;

    @Column(name = "rating")
    private Integer rating;

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

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Clinic getClinicid() {
        return clinicid;
    }

    public void setClinicid(Clinic clinicid) {
        this.clinicid = clinicid;
    }

    public Post getPostid() {
        return postid;
    }

    public void setPostid(Post postid) {
        this.postid = postid;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
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

    public LocalDate getExperience() {
        return experience;
    }

    public void setExperience(LocalDate experience) {
        this.experience = experience;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Specialization getSpecializationid() {
        return specializationid;
    }

    public void setSpecializationid(Specialization specializationid) {
        this.specializationid = specializationid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}