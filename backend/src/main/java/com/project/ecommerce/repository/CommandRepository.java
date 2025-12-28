package com.project.ecommerce.repository;

import com.project.ecommerce.entity.Command;
import com.project.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
    public List<Command> findByUser(User user);
}
