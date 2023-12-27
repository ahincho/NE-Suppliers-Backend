package com.unsa.suppliers.domain.repositories;

import com.unsa.suppliers.domain.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Integer> {
    Optional<StateEntity> findByName(String name);
}
