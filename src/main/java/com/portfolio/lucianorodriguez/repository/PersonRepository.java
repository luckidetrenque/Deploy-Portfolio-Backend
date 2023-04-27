package com.portfolio.lucianorodriguez.repository;

import com.portfolio.lucianorodriguez.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public Optional<Person> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
    public boolean existsByNameAndSurname(String name, String surname);
}
