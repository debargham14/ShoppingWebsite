package com.debargha.service;

import org.springframework.stereotype.Service;

import com.debargha.model.Apparel;
import com.debargha.model.ApparelDto;
import com.debargha.model.Preference;
import com.debargha.model.Sex;
import com.debargha.model.User;
import com.debargha.repository.ApparelRepository;
import com.debargha.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApparelServiceImpl implements ApparelService{
    private final ApparelRepository repository;
    private final UserRepository userRepository;

    public ApparelServiceImpl(ApparelRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    @Override
    public Apparel save(ApparelDto dto) {
        Apparel apparel = new Apparel(dto);
        return repository.save(apparel);
    }

    @Override
    public List<Apparel> listApparel(String email) {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        return repository.findAll()
                .stream()
                .filter(apparel -> apparel.getGender() == Sex.OTHERS || apparel.getGender() == user.getSex())
                .filter(apparel -> {
                    if(user.getPreference() == Preference.NEW_ARRIVAL)
                    {
                        return Duration.between(apparel.getCreatedAt().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() < 30;
                    }

                    return apparel.getSeason().getMonths().contains(LocalDate.now().getMonthValue());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Apparel> listApparel() {
        return repository.findAll();
    }
}
