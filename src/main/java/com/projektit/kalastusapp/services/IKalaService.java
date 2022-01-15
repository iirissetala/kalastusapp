package com.projektit.kalastusapp.services;

import com.projektit.kalastusapp.models.Kala;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IKalaService {
    List<Kala> getKalat();
    Kala getKala(Long id);
    List<Kala> setKalat(List<Kala> kalat);


}
