package com.projektit.kalastusapp.repositories;

import com.projektit.kalastusapp.models.Kala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KalaRepository extends JpaRepository<Kala, Long> {

}
