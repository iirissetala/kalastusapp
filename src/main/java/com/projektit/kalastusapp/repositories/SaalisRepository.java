package com.projektit.kalastusapp.repositories;

import com.projektit.kalastusapp.models.Saalis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaalisRepository extends JpaRepository<Saalis, Long> {
}
