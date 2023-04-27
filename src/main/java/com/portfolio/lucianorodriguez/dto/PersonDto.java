package com.portfolio.lucianorodriguez.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonDto {

    @NotBlank
    @Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
    private String name;
    @NotBlank
    @Size(max = 40, message = "El apellido no debe superar los 40 caracteres")
    private String surname;
    @NotBlank
    @Size(max = 40, message = "El título no debe superar los 40 caracteres")
    private String degree;
    @NotBlank
    @Size(max = 500, message = "La info no debe superar los 500 caracteres")
    private String info;
    @NotBlank
    private String photo;

    public PersonDto() {
    }

    public PersonDto(String name, String surname, String degree, String info, String photo) {
        this.name = name;
        this.surname = surname;
        this.degree = degree;
        this.info = info;
        this.photo = photo;
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
