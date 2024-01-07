package com.unsa.suppliers.domain.repositories;

import com.unsa.suppliers.domain.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
    boolean existsByName(String name);
    boolean existsByRuc(String ruc);
    List<SupplierEntity> findAllByStateId(Integer stateId);
}
