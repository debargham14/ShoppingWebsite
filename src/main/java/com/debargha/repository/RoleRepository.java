package com.debargha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debargha.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}