package com.projektit.kalastusapp.integration;

import com.projektit.kalastusapp.models.Kala;
import com.projektit.kalastusapp.services.IKalaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class KalatIntegrationTests {

    @Autowired
    IKalaService service;

    private static final String TEST_LAJI = "testikala";
    private static final String TEST_LAJI_MUOKATTU = "muokattuLaji";

    @Test
    public void createUpdateDeleteKalaOk() throws Exception {
        List<Kala> kalaList = service.getKalat();
        int size = kalaList.size();

        kalaList.add(new Kala(TEST_LAJI, 100, 1000));
        Long id = null;
        Kala foundKala = null;

        // insert ok
        List<Kala> saved = service.setKalat(kalaList);
        boolean found = false;
        for (Kala k : saved) {
            if (k.getLaji().equals(TEST_LAJI));
            id = k.getId();
            foundKala = k;
            found = true;
        }

        assertFalse(saved.isEmpty());
        assertEquals(size + 1, saved.size());
        assertTrue(found);

        // get one ok
        Kala getOne = service.getKala(id);
        assertEquals(TEST_LAJI, getOne.getLaji());

        // update ok
        foundKala.setLaji("muokattuLaji");
        List<Kala> updated = service.setKalat(saved);
        found = false;
        for (Kala k : updated) {
            if (k.getLaji().equals(TEST_LAJI_MUOKATTU));
            k.setPoisto(true);
            found = true;
        }

        assertTrue(found);

        // delete ok
        service.setKalat(updated);
        List<Kala> afterDelete = service.getKalat();
        found = false;
        for (Kala k : afterDelete) {
            if (k.getLaji().equals(TEST_LAJI_MUOKATTU));
            found = true;
        }

        assertFalse(found);
        assertEquals(size, afterDelete.size());

    }
}
