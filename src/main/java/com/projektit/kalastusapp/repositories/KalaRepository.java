package com.projektit.kalastusapp.repositories;

import com.projektit.kalastusapp.models.Kala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KalaRepository extends JpaRepository<Kala, Long> {
    @Query(value = "SELECT * FROM KALA WHERE SAALIS_ID=:saalisId", nativeQuery = true)
    List<Kala> findBySaalis(@Param("saalisId") Long saalisId);

}
