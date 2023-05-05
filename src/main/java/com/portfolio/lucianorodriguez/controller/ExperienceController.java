package com.portfolio.lucianorodriguez.controller;

import com.portfolio.lucianorodriguez.dto.ExperienceDto;
import com.portfolio.lucianorodriguez.entity.Experience;
import com.portfolio.lucianorodriguez.service.ExperienceService;
import com.portfolio.lucianorodriguez.utility.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.YEAR;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"https://lr-portfolio-frontend.web.app", "http://localhost:4200"})

public class ExperienceController {

    @Autowired
    ExperienceService experienceService;
    
    Calendar currentYear = Calendar.getInstance();

    @GetMapping("/experiences")
    public ResponseEntity<List<Experience>> listExperiences() {
        List<Experience> experiences = experienceService.listExperiences();
        return new ResponseEntity(experiences, HttpStatus.OK);
    }

    @GetMapping("/experiences/{id}")
    public ResponseEntity<Experience> findExperienceById(@PathVariable("id") Long id) {
        if (!experienceService.existsExperienceById(id)) {
            return new ResponseEntity(new Message("No existe ninguna experiencia con ese ID"), HttpStatus.NOT_FOUND);
        }

        Experience experience = experienceService.getExperienceById(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }

    @GetMapping("/experiences/position")
    public ResponseEntity<Experience> findExperienceByPosition(@RequestParam("p") String position) {
        if (!experienceService.existsExperienceByPosition(position)) {
            return new ResponseEntity(new Message("No existe ninguna experiencia con ese puesto"), HttpStatus.NOT_FOUND);
        }

        Experience experience = experienceService.getExperienceByPosition(position).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/experiences")
    public ResponseEntity<?> createExperience(@Valid @RequestBody ExperienceDto experienceDto) {
        if (experienceService.existsExperienceByCompany(experienceDto.getCompany()) && experienceService.existsExperienceByPosition(experienceDto.getPosition())) {
            return new ResponseEntity(new Message("Ya existe una experiencia con esa empresa y con ese puesto"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(experienceDto.getPosition()) || StringUtils.isBlank(experienceDto.getCompany()) || StringUtils.isBlank(experienceDto.getDescription())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }

        if (experienceDto.getDateFrom() < 1900 || experienceDto.getDateTo() > currentYear.get(YEAR)) {
            return new ResponseEntity(new Message("La fecha de la experiencia laboral debe ser mayor a 1900 y menor a " + currentYear.get(YEAR)), HttpStatus.BAD_REQUEST);
        }
        
        Experience experience = new Experience(experienceDto.getPosition(), experienceDto.getDateFrom(), experienceDto.getDateTo(), experienceDto.getCompany(), experienceDto.getDescription());
        experienceService.saveExperience(experience);

        return new ResponseEntity(new Message("Experiencia  creada coprrectamente"), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experiences/{id}")
    public ResponseEntity<Experience> deleteExperience(@PathVariable Long id) {
        if (!experienceService.existsExperienceById(id)) {
            return new ResponseEntity(new Message("No existe ninguna experiencia con ese ID"), HttpStatus.NOT_FOUND);
        }
        experienceService.deleteExperience(id);
        return new ResponseEntity(new Message("Experiencia eliminada correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experiences/{id}")
    public ResponseEntity<?> updateExperience(@PathVariable("id") Long id, @Valid @RequestBody ExperienceDto experienceDto) {
        if (!experienceService.existsExperienceById(id)) {
            return new ResponseEntity(new Message("No existe ninguna experiencia con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (experienceService.existsExperienceByPosition(experienceDto.getPosition()) && experienceService.getExperienceByPosition(experienceDto.getPosition()).get().getId() != id) {
            return new ResponseEntity(new Message("Ya existe una experiencia con ese puesto"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(experienceDto.getPosition()) || StringUtils.isBlank(experienceDto.getCompany()) || StringUtils.isBlank(experienceDto.getDescription())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }
        
        if (experienceDto.getDateFrom() < 1900 || experienceDto.getDateTo() > currentYear.get(YEAR)) {
            return new ResponseEntity(new Message("La fecha de la experiencia laboral debe ser mayor a 1900 y menor a " + currentYear.get(YEAR)), HttpStatus.BAD_REQUEST);
        }

        Experience experience = experienceService.getExperienceById(id).get();
        experience.setPosition(experienceDto.getPosition());
        experience.setDateFrom(experienceDto.getDateFrom());
        experience.setDateTo(experienceDto.getDateTo());
        experience.setCompany(experienceDto.getCompany());
        experience.setDescription(experienceDto.getDescription());
        
        experienceService.saveExperience(experience);

        return new ResponseEntity(new Message("Experiencia modificada correctamente"), HttpStatus.OK);
    }
}
