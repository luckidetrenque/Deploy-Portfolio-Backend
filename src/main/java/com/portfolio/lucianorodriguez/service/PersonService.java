package com.portfolio.lucianorodriguez.service;

import com.portfolio.lucianorodriguez.entity.Person;
import com.portfolio.lucianorodriguez.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> listPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Optional<Person> getPersonByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public boolean existsPersonById(Long id) {
        return personRepository.existsById(id);
    }

    public boolean existsPersonByNameAndSurname(String name, String surname) {
        return personRepository.existsByNameAndSurname(name, surname);
    }

}