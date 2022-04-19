package com.debargha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debargha.model.Apparel;
import com.debargha.model.Season;

import java.util.List;
import java.util.UUID;

public interface ApparelRepository extends JpaRepository<Apparel, UUID> {
    List<Apparel> findAllBySeason(Season season);
}