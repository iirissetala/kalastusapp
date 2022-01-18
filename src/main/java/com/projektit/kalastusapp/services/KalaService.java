package com.projektit.kalastusapp.services;

import com.projektit.kalastusapp.models.Kala;
import com.projektit.kalastusapp.repositories.KalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class KalaService implements IKalaService {
    private KalaRepository repository;

    @Autowired
    public KalaService(KalaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Kala> getKalat() {
        return repository.findAll();
    }

    @Override
    public List<Kala> getKalatBySaalis(Long saalisId) {
        return repository.findBySaalis(saalisId);
    }

    @Override
    public Kala getKala(Long id) {
        Optional<Kala> kala = repository.findById(id);
        return kala.orElse(new Kala());
    }

    @Override
    public List<Kala> setKalat(List<Kala> kalat) {
        List<Kala> returnList = new ArrayList<Kala>();
        if (kalat != null && !kalat.isEmpty()) {
            for (Kala k : kalat) {
               Kala returnKala = setKala(k);
               if (returnKala.getId() != null) {
                   returnList.add(returnKala);
               }
            }
        }
        return returnList;
    }

    private Kala setKala(Kala kala) {
        Kala kalaToSave = null;

        if (kala.getId() == null) {
            kalaToSave = new Kala();
        } else {
            kalaToSave = repository.findById(kala.getId()).orElseThrow(NoSuchElementException::new);
            if (kala.isPoisto()) {
                repository.delete(kalaToSave);
                return new Kala();
            }
        }
        kalaToSave.setLaji(kala.getLaji());
        kalaToSave.setPaino(kala.getPaino());
        kalaToSave.setPituus(kala.getPituus());
        kalaToSave.setViehe(kala.getViehe());
        kalaToSave.setSaalis(kala.getSaalis());

        Kala returnKala = repository.save(kalaToSave);
        repository.flush();

        return returnKala;
    }
}
