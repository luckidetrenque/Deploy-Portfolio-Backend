package com.portfolio.lucianorodriguez.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "institution")
    private String institution;
    @NotNull
    @Column(name = "logo")
    private String logo;
    @NotNull
    @Column(name = "degree")
    private String degree;
    @NotNull
    @Column(name = "date_from")
    private Integer dateFrom;
    @NotNull
    @Column(name = "date_to")
    private Integer dateTo;

    public Education() {
    }

    public Education(String institution, String logo, String degree, Integer dateFrom, Integer dateTo) {
        this.institution = institution;
        this.logo = logo;
        this.degree = degree;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
