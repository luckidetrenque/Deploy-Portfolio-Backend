package com.portfolio.lucianorodriguez.repository;

import com.portfolio.lucianorodriguez.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    
    public Optional<Experience> findByCompany(@Param("company") String position);
    public Optional<Experience> findByPosition(@Param("position") String position);
    public boolean existsByCompany(String company);
    public boolean existsByPosition(String position);
    
}
