package com.portfolio.lucianorodriguez.service;

import com.portfolio.lucianorodriguez.entity.Skill;
import com.portfolio.lucianorodriguez.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> listSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public Optional<Skill> getSkillByName(String name) {
        return skillRepository.findByName(name);
    }

    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public boolean existsSkillById(Long id) {
        return skillRepository.existsById(id);
    }

    public boolean existsSkillByName(String name) {
        return skillRepository.existsByName(name);
    }
}
