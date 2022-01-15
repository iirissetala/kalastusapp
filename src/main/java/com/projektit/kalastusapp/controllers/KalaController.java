package com.projektit.kalastusapp.controllers;

import com.projektit.kalastusapp.models.Kala;
import com.projektit.kalastusapp.services.IKalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/kalat")
public class KalaController {
    private IKalaService service;

    @Autowired
    public KalaController(IKalaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Kala> getKalat() {
        return service.getKalat();
    }

    @PutMapping
    public List<Kala> setKalat(@RequestBody List<Kala> kalat) {
        List<Kala> result = new ArrayList<Kala>();
        try {
            result = service.setKalat(kalat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Kalalistaa ei tallennettu");
        }
        return result;
    }


}
