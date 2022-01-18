package com.projektit.kalastusapp.services;

import com.projektit.kalastusapp.models.Kala;
import com.projektit.kalastusapp.models.Saalis;
import com.projektit.kalastusapp.repositories.KalaRepository;
import com.projektit.kalastusapp.repositories.SaalisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class SaalisService implements ISaalisService {
    private SaalisRepository repository;
    private IKalaService kalaService;

    @Autowired
    public SaalisService(SaalisRepository repository, IKalaService kalaService) {
        this.repository = repository;
        this.kalaService = kalaService;
    }

    @Override
    public List<Saalis> getSaaliit() {
        List<Saalis> saaliit = repository.findAll();
        return saaliit;
    }

    @Override
    public Saalis getSaalis(Long id) {
        Optional<Saalis> saalis = repository.findById(id);
        if (saalis.isPresent()) {
            return saalis.get();
        } else {
            return new Saalis();
        }
    }

    @Override
    public Saalis setSaalis(Saalis saalis) {
        Saalis saalisToSave = new Saalis();

        if (saalis.getId() != null) {
            saalisToSave.setId(saalis.getId());
            if (saalis.isPoisto()) {
                repository.deleteById(saalis.getId());
                return new Saalis();
            }
        }
        saalisToSave.setKalastaja(saalis.getKalastaja());
        saalisToSave.setPvm(saalis.getPvm());
        saalisToSave.setPaikka(saalis.getPaikka());

        Saalis returnSaalis = new Saalis();
        try {
            returnSaalis = repository.save(saalisToSave);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage() + ", Error cause: " + e.getCause());
        }

        if (saalis.getKalat() != null && !saalis.getKalat().isEmpty()) {
            for (Kala k : saalis.getKalat()) {
                k.setSaalis(returnSaalis);
            }
            List<Kala> kalaList = kalaService.setKalat(saalis.getKalat());
            returnSaalis.setKalat(kalaList);
        }

        repository.flush();
        return returnSaalis;
    }
}
