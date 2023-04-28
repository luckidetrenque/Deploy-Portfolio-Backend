package com.portfolio.lucianorodriguez.controller;

import com.portfolio.lucianorodriguez.dto.ProjectDto;
import com.portfolio.lucianorodriguez.entity.Project;
import com.portfolio.lucianorodriguez.service.ProjectService;
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

public class ProjectController {

    @Autowired
    ProjectService projectService;
    
    Calendar currentDate = Calendar.getInstance();

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> listProjects() {
        List<Project> projects = projectService.listProjects();
        return new ResponseEntity(projects, HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable("id") Long id) {
        if (!projectService.existsProjectById(id)) {
            return new ResponseEntity(new Message("No existe ningún proyecto con ese ID"), HttpStatus.NOT_FOUND);
        }

        Project project = projectService.getProjectById(id).get();
        return new ResponseEntity(project, HttpStatus.OK);
    }

    @GetMapping("/projects/name")
    public ResponseEntity<Project> findProjectByName(@RequestParam("name") String name) {
        if (!projectService.existsProjectByName(name)) {
            return new ResponseEntity(new Message("No existe ningún proyecto con ese nombre"), HttpStatus.NOT_FOUND);
        }

        Project project = projectService.getProjectByName(name).get();
        return new ResponseEntity(project, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("projects")
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDto projectDto) {
        if (projectService.existsProjectByName(projectDto.getName())) {
            return new ResponseEntity(new Message("Ya exsite un proyecto con ese nombre"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(projectDto.getName()) || projectDto.getDate() == null || StringUtils.isBlank(projectDto.getDescription())
                || StringUtils.isBlank(projectDto.getLink1()) || StringUtils.isBlank(projectDto.getLink2()) || StringUtils.isBlank(projectDto.getImage())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }

        if (projectDto.getDate() < 1900 || projectDto.getDate() > currentDate.get(YEAR)) {
            return new ResponseEntity(new Message("El año del proyecto debe ser mayor a 1900 y menor a " + currentDate.get(YEAR)), HttpStatus.BAD_REQUEST);
        }

        Project project = new Project(projectDto.getName(), projectDto.getDate(), projectDto.getDescription(), projectDto.getLink1(), projectDto.getLink2(), projectDto.getImage());
        projectService.saveProject(project);

        return new ResponseEntity(new Message("Proyecto creado correctamente"), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("projects/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id) {
        if (!projectService.existsProjectById(id)) {
            return new ResponseEntity(new Message("No existe ningún proyecto con ese nombre"), HttpStatus.NOT_FOUND);
        }

        projectService.deleteProject(id);
        return new ResponseEntity(new Message("Proyecto eliminado correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/projects/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long id, @Valid @RequestBody ProjectDto projectDto) {
        if (!projectService.existsProjectById(id)) {
            return new ResponseEntity(new Message("No existe ningún proyecto con ese nombre"), HttpStatus.NOT_FOUND);
        }
        if (projectService.existsProjectByName(projectDto.getName()) && projectService.getProjectByName(projectDto.getName()).get().getId() != id) {
            return new ResponseEntity(new Message("Ya existe un proyecto con ese nombre"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(projectDto.getName()) || projectDto.getDate() == null || StringUtils.isBlank(projectDto.getDescription())
                || StringUtils.isBlank(projectDto.getLink1()) || StringUtils.isBlank(projectDto.getLink2()) || StringUtils.isBlank(projectDto.getImage())) {
            return new ResponseEntity(new Message("No puede haber datos en blanco"), HttpStatus.BAD_REQUEST);
        }

        if (projectDto.getDate() < 1900 || projectDto.getDate() > currentDate.get(YEAR)) {
            return new ResponseEntity(new Message("El año del proyecto debe ser mayor a 1900 y menor a " + currentDate.get(YEAR)), HttpStatus.BAD_REQUEST);
        }
        
        Project project = projectService.getProjectById(id).get();
        project.setName(projectDto.getName());
        project.setDate(projectDto.getDate());
        project.setDescription(projectDto.getDescription());
        project.setLink1(projectDto.getLink1());
        project.setLink2(projectDto.getLink2());
        project.setImage(projectDto.getImage());

        projectService.saveProject(project);

        return new ResponseEntity(new Message("Proyecto modificado correctamente"), HttpStatus.OK);
    }
}
