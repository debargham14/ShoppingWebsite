package com.debargha.service;

import java.util.UUID;

import com.debargha.model.Purchase;

public interface PurchaseService {
    public Purchase purchase(UUID id, String username);
}
