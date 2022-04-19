package com.debargha.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.debargha.model.Purchase;
import com.debargha.model.User;
import com.debargha.model.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User save(UserDto dto);
    public User saveAdmin(UserDto dto);
    public boolean isRegistered(UserDto dto);
    public List<Purchase> getPurchases(String email);
}
