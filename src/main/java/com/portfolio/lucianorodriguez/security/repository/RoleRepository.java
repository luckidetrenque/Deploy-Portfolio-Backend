
package com.portfolio.lucianorodriguez.security.repository;

import com.portfolio.lucianorodriguez.security.entity.Role;
import com.portfolio.lucianorodriguez.security.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    public Optional<Role> findByType(RoleType type);
}
