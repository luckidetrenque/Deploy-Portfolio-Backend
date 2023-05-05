package com.portfolio.lucianorodriguez.controller;

import com.portfolio.lucianorodriguez.dto.EducationDto;
import com.portfolio.lucianorodriguez.entity.Education;
import com.portfolio.lucianorodriguez.service.EducationService;
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
public class EducationController {

    @Autowired
    EducationService educationService;
    
    Calendar currentYear = Calendar.getInstance();

    @GetMapping("/education")
    public ResponseEntity<List<Education>> listEducation() {
        List<Education> education = educationService.listEducation();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @GetMapping("/education/{id}")
    public ResponseEntity<Education> findEducationById(@PathVariable("id") Long id) {
        if (!educationService.existsEducationById(id)) {
            return new ResponseEntity(new Message("No existe ninguna education con ese ID"), HttpStatus.NOT_FOUND);
        }
        Education education = educationService.getEducationById(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @GetMapping("/education/institution")
    public ResponseEntity<Education> findEducationByInstitution(@RequestParam("i") String institution) {
        if (!educationService.existsEducationByInstitution(institution)) {
            return new ResponseEntity(new Message("No existe ninguna educación con esa institución"), HttpStatus.NOT_FOUND);
        }
        Education education = educationService.getEducationByInstitution(institution).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @GetMapping("/education/degree")
    public ResponseEntity<Education> findEducationByDegree(@RequestParam("d") String degree) {
        if (!educationService.existsEducationByDegree(degree)) {
            return new ResponseEntity(new Message("No existe ninguna educación con esa carrera"), HttpStatus.NOT_FOUND);
        }
        Education education = educationService.getEducationByDegree(degree).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/education")
    public ResponseEntity<?> createEducation(@Valid @RequestBody EducationDto educationDto) {
        if (educationService.existsEducationByInstitution(educationDto.getInstitution()) && educationService.existsEducationByDegree(educationDto.getDegree())) {
            return new ResponseEntity(new Message("Ya existe una educación con esa institución y carrera"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educationDto.getInstitution()) || StringUtils.isBlank(educationDto.getDegree())
                || StringUtils.isBlank(educationDto.getLogo())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }
        
        if (educationDto.getDateFrom() < 1900 || educationDto.getDateTo() > currentYear.get(YEAR)) {
            return new ResponseEntity(new Message("La fecha de la carrera/curso debe ser mayor a 1900 y menor a " + currentYear.get(YEAR)), HttpStatus.BAD_REQUEST);
        }

        Education education = new Education(educationDto.getInstitution(), educationDto.getLogo(), educationDto.getDegree(),
                educationDto.getDateFrom(), educationDto.getDateTo());
        educationService.saveEducation(education);
        return new ResponseEntity(new Message("Educación creada correctamente"), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/education/{id}")
    public ResponseEntity<Education> deleteEducation(@PathVariable("id") Long id) {
        if (!educationService.existsEducationById(id)) {
            return new ResponseEntity(new Message("No existe ninguna education con ese ID"), HttpStatus.NOT_FOUND);
        }
        educationService.deleteEducation(id);
        return new ResponseEntity(new Message("Educación eliminada correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/education/{id}")
    public ResponseEntity<?> updateEducation(@PathVariable("id") Long id, @Valid @RequestBody EducationDto educationDto) {
        if (!educationService.existsEducationById(id)) {
            return new ResponseEntity(new Message("No existe ninguna educación con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (educationService.existsEducationByInstitution(educationDto.getInstitution()) && educationService.existsEducationByDegree(educationDto.getDegree()) && educationService.getEducationByDegree(educationDto.getDegree()).get().getId() != id) {
            return new ResponseEntity(new Message("Ya existe una educación con esa institución y carrera"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(educationDto.getInstitution()) || StringUtils.isBlank(educationDto.getDegree())
                || StringUtils.isBlank(educationDto.getLogo())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }
        if (educationDto.getDateFrom() < 1900 || educationDto.getDateTo() > currentYear.get(YEAR)) {
            return new ResponseEntity(new Message("La fecha de la carrera/curso debe ser mayor a 1900 y menor a " + currentYear.get(YEAR)), HttpStatus.BAD_REQUEST);
        }
        
        Education education = educationService.getEducationById(id).get();
        education.setInstitution(educationDto.getInstitution());
        education.setLogo(educationDto.getLogo());
        education.setDegree(educationDto.getDegree());
        education.setDateFrom(educationDto.getDateFrom());
        education.setDateTo(educationDto.getDateTo());

        educationService.saveEducation(education);

        return new ResponseEntity(new Message("Educación modificada correctamente"), HttpStatus.OK);
    }
}
