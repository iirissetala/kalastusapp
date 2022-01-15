package com.projektit.kalastusapp.services;

import com.projektit.kalastusapp.models.Kala;
import com.projektit.kalastusapp.models.Saalis;
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
        for (Saalis s : saaliit) {
            s.setKalat(kalaService.getKalatBySaalis(s.getId()));
        }
        return saaliit;
    }

    @Override
    public Saalis getSaalis(Long id) {
        Optional<Saalis> saalis = repository.findById(id);
        if (saalis.isPresent()) {
            List<Kala> kalat = kalaService.getKalatBySaalis(id);
            saalis.get().setKalat(kalat);
        } else {
            throw new NoSuchElementException();
        }
        return saalis.get();
    }

    @Override
    public Saalis setSaalis(Saalis saalis) {
        Saalis saalisToSave = null;
        List<Kala> kalat = new ArrayList<>();
        if (saalis.getKalat() != null && !saalis.getKalat().isEmpty()) {
            kalat = kalaService.setKalat(saalis.getKalat());
            for (Kala k : kalat) {
                k.setSaalis(saalis);
            }
        }

        if (saalis.getId() == null) {
            saalisToSave = new Saalis();

        } else {
            saalisToSave = repository.findById(saalis.getId()).orElseThrow(NoSuchElementException::new);
        }
        saalisToSave.setKalastaja(saalis.getKalastaja());
        saalisToSave.setPvm(saalis.getPvm());
        saalisToSave.setPaikka(saalis.getPaikka());
        saalisToSave.setKalat(kalat);

        Saalis returnSaalis = new Saalis();
        try {
            returnSaalis = repository.save(saalisToSave);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage() + ", Error cause: " + e.getCause());
        }
        repository.flush();
        return returnSaalis;
    }
}
