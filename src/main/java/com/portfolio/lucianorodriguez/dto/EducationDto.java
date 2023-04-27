package com.portfolio.lucianorodriguez.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EducationDto {

    @NotBlank
    @Size(max = 80, message = "La institución no debe superar los 80 caracteres")
    private String institution;
    @NotBlank
    private String logo;
    @NotBlank
    @Size(max = 40, message = "El título obtenido no debe superar los 40 caracteres")
    private String degree;
    @Min(1900)
    private Integer dateFrom;
    @Max(2023)
    private Integer dateTo;

    public EducationDto() {
    }

    public EducationDto(String institution, String logo, String degree, Integer dateFrom, Integer dateTo) {
        this.institution = institution;
        this.logo = logo;
        this.degree = degree;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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
}
