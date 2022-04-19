package com.debargha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debargha.model.Role;
import com.debargha.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String s);
    List<User> findAllByRole(Role role);
}