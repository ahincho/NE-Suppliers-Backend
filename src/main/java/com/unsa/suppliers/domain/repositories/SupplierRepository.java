package com.unsa.suppliers.domain.repositories;

import com.unsa.suppliers.domain.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
    Optional<SupplierEntity> findByName(String name);
    Optional<SupplierEntity> findByStateId(Integer stateId);
}
