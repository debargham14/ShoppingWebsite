package com.debargha.service;

import java.util.List;

import com.debargha.model.Apparel;
import com.debargha.model.ApparelDto;

public interface ApparelService {
    public Apparel save(ApparelDto dto);
    public List<Apparel> listApparel(String email);
    public List<Apparel> listApparel();
}
