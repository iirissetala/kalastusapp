package com.projektit.kalastusapp.services;

import com.projektit.kalastusapp.models.Saalis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISaalisService {
    List<Saalis> getSaaliit();
    Saalis getSaalis(Long id);
    Saalis setSaalis(Saalis saalis);
}
