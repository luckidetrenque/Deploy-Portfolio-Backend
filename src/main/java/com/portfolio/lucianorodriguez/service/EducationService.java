package com.portfolio.lucianorodriguez.service;

import com.portfolio.lucianorodriguez.entity.Education;
import com.portfolio.lucianorodriguez.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public List<Education> listEducation() {
        return educationRepository.findAll();
    }

    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    public Optional<Education> getEducationByInstitution(String institution) {
        return educationRepository.findByInstitution(institution);
    }

    public Optional<Education> getEducationByDegree(String degree) {
        return educationRepository.findByDegree(degree);
    }

    public void saveEducation(Education education) {
        educationRepository.save(education);
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }

    public boolean existsEducationById(Long id) {
        return educationRepository.existsById(id);
    }

    public boolean existsEducationByInstitution(String institution) {
        return educationRepository.existsByInstitution(institution);
    }

    public boolean existsEducationByDegree(String degree) {
        return educationRepository.existsByDegree(degree);
    }
}
