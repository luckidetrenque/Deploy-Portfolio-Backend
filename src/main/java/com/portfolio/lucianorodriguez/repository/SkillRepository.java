package com.portfolio.lucianorodriguez.repository;

import com.portfolio.lucianorodriguez.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    public Optional<Skill> findByName(@Param("name") String name);
    public boolean existsByName(String name);
}
