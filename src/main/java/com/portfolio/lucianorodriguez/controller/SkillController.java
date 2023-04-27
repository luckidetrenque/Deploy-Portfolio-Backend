package com.portfolio.lucianorodriguez.controller;

import com.portfolio.lucianorodriguez.dto.SkillDto;
import com.portfolio.lucianorodriguez.entity.Skill;
import com.portfolio.lucianorodriguez.service.SkillService;
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

public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> listSkills() {
        List<Skill> skills = skillService.listSkills();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @GetMapping("/skills/{id}")
    public ResponseEntity<Skill> findSkillById(@PathVariable("id") Long id) {
        if (!skillService.existsSkillById(id)) {
            return new ResponseEntity(new Message("No existe ninguna skill con ese ID"), HttpStatus.NOT_FOUND);
        }

        Skill skill = skillService.getSkillById(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }

    @GetMapping("/skills/name")
    public ResponseEntity<Skill> findSkillByName(@RequestParam("name") String name) {
        if (!skillService.existsSkillByName(name)) {
            return new ResponseEntity(new Message("No existe ninguna skill con ese nombre"), HttpStatus.NOT_FOUND);
        }

        Skill skill = skillService.getSkillByName(name).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/skills")
    public ResponseEntity<?> createSkill(@Valid @RequestBody SkillDto skillDto) {
        if (skillService.existsSkillByName(skillDto.getName())) {
            return new ResponseEntity(new Message("Ya existe una skill con ese nombre"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(skillDto.getName()) || skillDto.getLevel() == null || StringUtils.isBlank(skillDto.getImage())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }

        if (skillDto.getLevel() < 0 || skillDto.getLevel() > 100) {
            return new ResponseEntity(new Message("El nivel de skill debe ser entre 0 y 100"), HttpStatus.BAD_REQUEST);
        }

        Skill skill = new Skill(skillDto.getName(), skillDto.getLevel(), skillDto.getImage());
        skillService.saveSkill(skill);

        return new ResponseEntity(new Message("Skill creada coprrectamente"), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Skill> deleteSkill(@PathVariable Long id) {
        if (!skillService.existsSkillById(id)) {
            return new ResponseEntity(new Message("No existe ninguna experiencia con ese ID"), HttpStatus.NOT_FOUND);
        }
        skillService.deleteSkill(id);
        return new ResponseEntity(new Message("Skill eliminada correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skills/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable("id") Long id, @Valid @RequestBody SkillDto skillDto) {
        if (!skillService.existsSkillById(id)) {
            return new ResponseEntity(new Message("No existe ninguna skill con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (skillService.existsSkillByName(skillDto.getName())) {
            return new ResponseEntity(new Message("Ya existe una skill con ese nombre"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(skillDto.getName()) || skillDto.getLevel() == null || StringUtils.isBlank(skillDto.getImage())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }

        if (skillDto.getLevel() < 0 || skillDto.getLevel() > 100) {
            return new ResponseEntity(new Message("El nivel de skill debe ser entre 0 y 100"), HttpStatus.BAD_REQUEST);
        }

        Skill skill = skillService.getSkillById(id).get();
        skill.setName(skillDto.getName());
        skill.setLevel(skillDto.getLevel());

        skillService.saveSkill(skill);

        return new ResponseEntity(new Message("Skill modificada correctamente"), HttpStatus.OK);

    }
}
