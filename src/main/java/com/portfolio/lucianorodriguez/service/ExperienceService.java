package com.portfolio.lucianorodriguez.service;

import com.portfolio.lucianorodriguez.entity.Experience;
import com.portfolio.lucianorodriguez.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    ExperienceRepository experienceRepository;

    public List<Experience> listExperiences() {
        return experienceRepository.findAll();
    }

    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    public Optional<Experience> getExperienceByPosition(String position) {
        return experienceRepository.findByPosition(position);
    }

    public void saveExperience(Experience experience) {
        experienceRepository.save(experience);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    public boolean existsExperienceById(Long id) {
        return experienceRepository.existsById(id);
    }

    public boolean existsExperienceByPosition(String position) {
        return experienceRepository.existsByPosition(position);
    }
}
