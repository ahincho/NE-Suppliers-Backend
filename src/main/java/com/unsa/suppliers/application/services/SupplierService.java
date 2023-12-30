package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.StateEntity;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import com.unsa.suppliers.domain.exceptions.suppliers.*;
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
    public List<SupplierEntity> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    public SupplierEntity findSupplierById(Integer id) throws SupplierNotFoundException {
        return recoverSupplierByIdOrThrowNotFoundException(id);
    }
    @Transactional
    public SupplierEntity createSupplier(SupplierEntity supplierEntity) throws SupplierDuplicatedNameException, SupplierDuplicatedRucException, StateNotFoundException {
        throwExceptionsIfIsDuplicated(supplierEntity);
        supplierEntity.setState(findStateByNameOrThrowNotFoundException("ACTIVE"));
        return supplierRepository.save(supplierEntity);
    }
    @Transactional
    public void updateSupplier(Integer id, SupplierEntity supplierEntity) throws SupplierNotFoundException, SupplierDuplicatedNameException, SupplierDuplicatedRucException {
        SupplierEntity supplier = recoverSupplierByIdOrThrowNotFoundException(id);
        throwExceptionsIfIsDuplicated(supplierEntity);
        supplierEntity.setId(id);
        supplierEntity.setState(supplier.getState());
        supplierRepository.save(supplierEntity);
    }
    public List<SupplierEntity> getAllActiveSuppliers() throws StateNotFoundException {
        return supplierRepository.findAllByStateId(findStateByNameOrThrowNotFoundException("ACTIVE").getId());
    }
    @Transactional
    public void changeSupplierState(Integer id, String state) throws SupplierNotFoundException, StateNotFoundException {
        SupplierEntity supplierEntity = recoverSupplierByIdOrThrowNotFoundException(id);
        supplierEntity.setState(findStateByNameOrThrowNotFoundException(state));
        supplierRepository.save(supplierEntity);
    }
    // Private Auxiliary Methods because Boiler Code
    private SupplierEntity recoverSupplierByIdOrThrowNotFoundException(Integer id) throws SupplierNotFoundException {
        Optional<SupplierEntity> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) { throw new SupplierNotFoundException(); }
        return optionalSupplier.get();
    }
    private void throwExceptionsIfIsDuplicated(SupplierEntity supplierEntity) throws SupplierDuplicatedNameException, SupplierDuplicatedRucException {
        if (supplierRepository.existsByName(supplierEntity.getName())) { throw new SupplierDuplicatedNameException(); }
        if (supplierRepository.existsByRuc(supplierEntity.getRuc())) { throw new SupplierDuplicatedRucException(); }
    }
    private StateEntity findStateByNameOrThrowNotFoundException(String name) throws StateNotFoundException {
        Optional<StateEntity> optionalState = stateRepository.findByName(name);
        if (optionalState.isEmpty()) { throw new StateNotFoundException(); }
        return optionalState.get();
    }
}
