package com.debargha.service;

import java.util.UUID;

import com.debargha.model.Deal;
import com.debargha.model.DealDto;

public interface DealService {
    public double getDiscountedPrice(UUID apparelId);
    public Deal save(DealDto dealDto);
}
