package com.debargha.service;

import org.springframework.stereotype.Service;

import com.debargha.model.Purchase;
import com.debargha.repository.ApparelRepository;
import com.debargha.repository.PurchaseRepository;
import com.debargha.repository.UserRepository;

import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final ApparelRepository apparelRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(ApparelRepository apparelRepository, UserRepository userRepository, PurchaseRepository purchaseRepository) {
        this.apparelRepository = apparelRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase purchase(UUID apparelId, String username) {
        Purchase purchase = new Purchase();
        purchase.setApparel(apparelRepository.getById(apparelId));
        purchase.setUser(userRepository.findByEmail(username));
        return purchaseRepository.save(purchase);
    }
}
