package com.portfolio.lucianorodriguez.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "experiences")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "position")
    private String position;
    @NotNull
    @Column(name = "date_from")
    private Integer dateFrom;
    @NotNull
    @Column(name = "date_to")
    private Integer dateTo;
    @NotNull
    @Column(name = "company")
    private String company;
    @NotNull
    @Column(name = "description", columnDefinition="text")
    private String description;

    public Experience() {
    }

    public Experience(String position, Integer dateFrom, Integer dateTo, String company, String description) {
        this.position = position;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.company = company;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
