package com.project.ecommerce.repository;

import com.project.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsernameAndPassword(String username, String password);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);
}
