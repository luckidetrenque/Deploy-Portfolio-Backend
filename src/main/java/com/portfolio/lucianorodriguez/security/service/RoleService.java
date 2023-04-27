
package com.portfolio.lucianorodriguez.security.service;

import com.portfolio.lucianorodriguez.security.entity.Role;
import com.portfolio.lucianorodriguez.security.enums.RoleType;
import com.portfolio.lucianorodriguez.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }
    
    public Optional<Role> getRoleByType(RoleType type) {
        return roleRepository.findByType(type);
    }
    
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
