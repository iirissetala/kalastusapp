package com.projektit.kalastusapp.integration;

import com.projektit.kalastusapp.models.Saalis;
import com.projektit.kalastusapp.services.ISaalisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.*;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class SaalisIntegrationTests {

    @Autowired
    ISaalisService service;

    private static final String TEST_KALASTAJA = "testikalastaja";
    private static final String TEST_KALASTAJA_MUOKATTU = "muokattuKalastaja";

    @Test
    public void createUpdateDeleteSaalisOk() throws Exception {
        Saalis saalis = new Saalis();

        saalis.setKalastaja(TEST_KALASTAJA);
        saalis.setPvm(LocalDate.now());

        // insert ok
        Saalis saved = service.setSaalis(saalis);
        Long id = saved.getId();

        assertNotNull("Tallennus ei onnistunut.", saved.getId());

        // saalis löytyy id:llä
        Saalis found = service.getSaalis(id);
        assertNotNull("Saaliin id on null", found.getId());
        assertEquals("Kalastajan nimi ei täsmää.", TEST_KALASTAJA, found.getKalastaja());

        // update ok
        found.setKalastaja(TEST_KALASTAJA_MUOKATTU);
        Saalis updated = service.setSaalis(found);

        assertEquals("Muokatun saaliin nimi ei täsmää.", TEST_KALASTAJA_MUOKATTU, updated.getKalastaja());

        // delete ok
        updated.setPoisto(true);
        service.setSaalis(updated);

        // poiston jälkeen löytyy saalis null-arvoilla
        Saalis afterDelete = service.getSaalis(id);
        assertNull("Poisto ei onnistunut", afterDelete.getId());

    }
}
