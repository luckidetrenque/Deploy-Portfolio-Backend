package com.portfolio.lucianorodriguez.security.entity;

import com.portfolio.lucianorodriguez.security.enums.RoleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private RoleType type;

    public Role() {
    }

    public Role(RoleType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return type;
    }

    public void setRoleType(RoleType type) {
        this.type = type;
    }

}
