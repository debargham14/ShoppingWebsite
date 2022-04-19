package com.debargha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debargha.model.Deal;

import java.util.UUID;

public interface DealRepository extends JpaRepository<Deal, Long> {
    Deal findByApparel_Id(UUID id);
}