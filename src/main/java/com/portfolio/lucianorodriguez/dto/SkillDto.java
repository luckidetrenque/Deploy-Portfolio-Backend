package com.portfolio.lucianorodriguez.dto;

import javax.validation.constraints.*;

public class SkillDto {

    @NotBlank
    @Size(max = 30, message = "El nombre no debe seperar los 30 caracteres")
    private String name;
    @Min(0)
    @Max(100)
    private Integer level;
    @NotBlank
    private String image;

    public SkillDto() {
    }

    public SkillDto(String name, Integer level, String image) {
        this.name = name;
        this.level = level;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
