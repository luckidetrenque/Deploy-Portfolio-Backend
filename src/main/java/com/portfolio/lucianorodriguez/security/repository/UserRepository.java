package com.portfolio.lucianorodriguez.security.repository;

import com.portfolio.lucianorodriguez.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
