package com.portfolio.lucianorodriguez.repository;

import com.portfolio.lucianorodriguez.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    public Optional<Education> findByInstitution(@Param("institution") String institution);
    public boolean existsByInstitution(String institution);
    public Optional<Education> findByDegree(@Param("degree") String degree);
    public boolean existsByDegree(String degree);
}
