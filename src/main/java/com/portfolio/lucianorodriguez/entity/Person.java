package com.portfolio.lucianorodriguez.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @NotNull
    @Column(name="name")
    private String name;
    @NotNull
    @Column(name="surname")
    private String surname;
    @NotNull
    @Column(name="degree")
    private String degree;
    @NotNull
    @Column(name="info", columnDefinition="text")
    private String info;
    @NotNull
    @Column(name="photo")
    private String photo;

    public Person() {
    }

    public Person(String name, String surname, String degree, String info, String photo) {
        this.name = name;
        this.surname = surname;
        this.degree = degree;
        this.info = info;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
