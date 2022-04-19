package com.debargha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debargha.model.Purchase;
import com.debargha.model.User;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);
}