package com.portfolio.lucianorodriguez.repository;

import com.portfolio.lucianorodriguez.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    public Optional<Project> findByName(@Param("name") String name);
    public boolean existsByName(String name);
}
