package com.portfolio.lucianorodriguez.controller;

import com.portfolio.lucianorodriguez.dto.PersonDto;
import com.portfolio.lucianorodriguez.entity.Person;
import com.portfolio.lucianorodriguez.service.PersonService;
import com.portfolio.lucianorodriguez.utility.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"", "http://localhost:4200"})
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> listPersons() {
        List<Person> persons = personService.listPersons();
        return new ResponseEntity(persons, HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable("id") Long id) {
        if (!personService.existsPersonById(id)) {
            return new ResponseEntity(new Message("No existe ninguna persona con ese ID"), HttpStatus.NOT_FOUND);
        }
        Person person = personService.getPersonById(id).get();
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @GetMapping("/persons/fullname")
    public ResponseEntity<Person> findPersonByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        if (!personService.existsPersonByNameAndSurname(name, surname)) {
            return new ResponseEntity(new Message("No existe ninguna persona con ese nombre y apellido"), HttpStatus.NOT_FOUND);
        }
        Person person = personService.getPersonByNameAndSurname(name, surname).get();
        return new ResponseEntity(person, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persons")
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonDto personDto) {
        if (personService.existsPersonByNameAndSurname(personDto.getName(), personDto.getSurname())) {
            return new ResponseEntity(new Message("Ya existe una persona con ese nombre y apellido"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personDto.getName()) || StringUtils.isBlank(personDto.getSurname())
                || StringUtils.isBlank(personDto.getDegree()) || StringUtils.isBlank(personDto.getInfo()) || StringUtils.isBlank(personDto.getPhoto())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }
        Person person = new Person(personDto.getName(), personDto.getSurname(), personDto.getDegree(),
                personDto.getInfo(), personDto.getPhoto());
        personService.savePerson(person);
        return new ResponseEntity(new Message("Persona creada correctamente"), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable("id") Long id) {
        if (!personService.existsPersonById(id)) {
            return new ResponseEntity(new Message("No existe ninguna persona con ese ID"), HttpStatus.NOT_FOUND);
        }
        personService.deletePerson(id);
        return new ResponseEntity(new Message("Persona eliminada correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persons/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable("id") Long id, @Valid @RequestBody PersonDto personDto) {
        if (!personService.existsPersonById(id)) {
            return new ResponseEntity(new Message("No existe ninguna persona con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (personService.existsPersonByNameAndSurname(personDto.getName(), personDto.getSurname()) && 
                personService.getPersonByNameAndSurname(personDto.getName(), personDto.getSurname()).get().getId() != id) {
            return new ResponseEntity(new Message("Ya existe una persona con ese nombre y apellido"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personDto.getName()) || StringUtils.isBlank(personDto.getSurname())
                || StringUtils.isBlank(personDto.getDegree()) || StringUtils.isBlank(personDto.getInfo()) || StringUtils.isBlank(personDto.getPhoto())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }
        Person person = personService.getPersonById(id).get();
        person.setName(personDto.getName());
        person.setSurname(personDto.getSurname());
        person.setDegree(personDto.getDegree());
        person.setInfo(personDto.getInfo());
        person.setPhoto(personDto.getPhoto());

        personService.savePerson(person);

        return new ResponseEntity(new Message("Persona modificada correctamente"), HttpStatus.OK);
    }
}
