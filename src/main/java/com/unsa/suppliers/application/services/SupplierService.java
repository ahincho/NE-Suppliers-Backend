package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.StateEntity;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import com.unsa.suppliers.domain.exceptions.suppliers.SupplierDuplicatedException;
import com.unsa.suppliers.domain.exceptions.suppliers.SupplierNotFoundException;
import com.unsa.suppliers.domain.repositories.StateRepository;
import com.unsa.suppliers.domain.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final StateRepository stateRepository;
    public SupplierService(SupplierRepository supplierRepository, StateRepository stateRepository) {
        this.supplierRepository = supplierRepository;
        this.stateRepository = stateRepository;
    }
    public List<SupplierEntity> getAll() {
        return supplierRepository.findAll();
    }
    public SupplierEntity findSupplierById(Integer id) throws SupplierNotFoundException {
        Optional<SupplierEntity> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) { throw new SupplierNotFoundException(); }
        return optionalSupplier.get();
    }
    @Transactional
    public SupplierEntity createSupplier(SupplierEntity supplierEntity) throws SupplierDuplicatedException, StateNotFoundException {
        Optional<SupplierEntity> optionalSupplier = supplierRepository.findByName(supplierEntity.getName());
        if (optionalSupplier.isPresent()) { throw new SupplierDuplicatedException(); }
        Optional<StateEntity> optionalState = stateRepository.findByName("ACTIVE");
        if (optionalState.isEmpty()) { throw new StateNotFoundException(); }
        supplierEntity.setState(optionalState.get());
        return supplierRepository.save(supplierEntity);
    }
    @Transactional
    public void updateSupplier(Integer id, SupplierEntity supplierEntity) throws SupplierNotFoundException, SupplierDuplicatedException {
        Optional<SupplierEntity> optionalSupplierId = supplierRepository.findById(id);
        if (optionalSupplierId.isEmpty()) { throw new SupplierNotFoundException(); }
        Optional<SupplierEntity> optionalSupplierName = supplierRepository.findByName(supplierEntity.getName());
        if (optionalSupplierName.isPresent()) { throw new SupplierDuplicatedException(); }
        supplierEntity.setId(id);
        supplierRepository.save(supplierEntity);
    }
    @Transactional
    public void changeSupplierState(Integer id, String state) throws SupplierNotFoundException, StateNotFoundException {
        Optional<SupplierEntity> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) { throw new SupplierNotFoundException(); }
        Optional<StateEntity> optionalState = stateRepository.findByName(state);
        if (optionalState.isEmpty()) { throw new StateNotFoundException(); }
        SupplierEntity supplierEntity = optionalSupplier.get();
        supplierEntity.setState(optionalState.get());
        supplierRepository.save(supplierEntity);
    }
}
