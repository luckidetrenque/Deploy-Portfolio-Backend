package com.portfolio.lucianorodriguez.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProjectDto {

    @NotBlank
    @Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
    private String name;
    @Min(1900)
    private Integer date;
    @NotBlank
    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
    private String description;
    @NotBlank
    private String link1;
    @NotBlank
    private String link2;
    @NotBlank
    private String image;

    public ProjectDto() {
    }

    public ProjectDto(String name, Integer date, String description, String link1, String link2, String image) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.link1 = link1;
        this.link2 = link2;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
