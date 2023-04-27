package com.portfolio.lucianorodriguez.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pojects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "`date`")
    private Integer date;
    @NotNull
    @Column(name = "description", columnDefinition="text")
    private String description;
    @NotNull
    @Column(name = "link_web", columnDefinition="varchar(255) default '#'")
    private String link1;
    @NotNull
    @Column(name = "link_github", columnDefinition="varchar(255) default '#'")
    private String link2;
    @NotNull
    @Column(name = "image")
    private String image;

    public Project() {
    }

    public Project(String name, Integer date, String description, String link1, String link2, String image) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.link1 = link1;
        this.link2 = link2;
        this.image = image;
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
