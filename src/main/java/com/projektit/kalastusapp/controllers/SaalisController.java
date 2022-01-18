package com.projektit.kalastusapp.controllers;

import com.projektit.kalastusapp.models.Saalis;
import com.projektit.kalastusapp.services.ISaalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/saaliit")
public class SaalisController {
    private ISaalisService service;

    @Autowired
    public SaalisController(ISaalisService service) {
        this.service = service;
    }

    @GetMapping
    public List<Saalis> getSaaliit() {
        return service.getSaaliit();
    }

    @GetMapping("/{id}")
    public Saalis getSaalis(@PathVariable Long id) {
        return service.getSaalis(id);
    }

    @PutMapping
    public Saalis setSaalis(@RequestBody Saalis saalis) {
        Saalis result = new Saalis();
        try {
            result = service.setSaalis(saalis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
