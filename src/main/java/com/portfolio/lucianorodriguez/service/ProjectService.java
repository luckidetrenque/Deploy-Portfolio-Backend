package com.portfolio.lucianorodriguez.service;

import com.portfolio.lucianorodriguez.entity.Project;
import com.portfolio.lucianorodriguez.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public List<Project> listProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Optional<Project> getProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public boolean existsProjectById(Long id) {
        return projectRepository.existsById(id);
    }

    public boolean existsProjectByName(String name) {
        return projectRepository.existsByName(name);
    }
}
