package com.portfolio.lucianorodriguez.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ExperienceDto {

    @NotBlank
    @Size(max = 40, message = "El puesto no debe superar los 40 caracteres")
    private String position;
    @Min(1900)
    private Integer dateFrom;
    @Max(2023)
    private Integer dateTo;
    @NotBlank
    private String company;
    @NotBlank
    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
    private String description;

    public ExperienceDto() {
    }

    public ExperienceDto(String position, Integer dateFrom, Integer dateTo, String company, String description) {
        this.position = position;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.company = company;
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Integer dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Integer getDateTo() {
        return dateTo;
    }

    public void setDateTo(Integer dateTo) {
        this.dateTo = dateTo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
