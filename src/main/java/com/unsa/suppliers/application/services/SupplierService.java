package com.unsa.suppliers.application.services;

import static com.unsa.suppliers.application.services.StateService.ACTIVE_STATE;

import com.unsa.suppliers.domain.entities.StateEntity;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import com.unsa.suppliers.domain.exceptions.suppliers.*;
import com.unsa.suppliers.domain.repositories.StateRepository;
import com.unsa.suppliers.domain.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

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
    public List<SupplierEntity> getAllActiveSuppliers() throws StateNotFoundException {
        StateEntity stateEntity = stateRepository.findByName(ACTIVE_STATE).orElseThrow(StateNotFoundException::new);
        return supplierRepository.findAllByStateId(stateEntity.getId());
    }
    public SupplierEntity findSupplierById(Integer id) throws SupplierNotFoundException {
        return supplierRepository.findById(id).orElseThrow(SupplierNotFoundException::new);
    }
    @Transactional
    public SupplierEntity createSupplier(SupplierEntity supplierEntity) throws SupplierDuplicatedNameException, SupplierDuplicatedRucException, StateNotFoundException {
        if (supplierRepository.existsByName(supplierEntity.getName())) { throw new SupplierDuplicatedNameException(); }
        if (supplierRepository.existsByRuc(supplierEntity.getRuc())) { throw new SupplierDuplicatedRucException(); }
        supplierEntity.setState(stateRepository.findByName(ACTIVE_STATE).orElseThrow(StateNotFoundException::new));
        return supplierRepository.save(supplierEntity);
    }
    @Transactional
    public void updateSupplier(Integer id, SupplierEntity supplierEntity) throws SupplierNotFoundException, SupplierDuplicatedNameException, SupplierDuplicatedRucException {
        SupplierEntity existingSupplier = supplierRepository.findById(id).orElseThrow(SupplierNotFoundException::new);
        if (!existingSupplier.getName().equals(supplierEntity.getName())) {
            if (supplierRepository.existsByName(supplierEntity.getName())) { throw new SupplierDuplicatedNameException(); }
        }
        if (!existingSupplier.getRuc().equals(supplierEntity.getRuc())) {
            if (supplierRepository.existsByRuc(supplierEntity.getRuc())) { throw new SupplierDuplicatedRucException(); }
        }
        supplierEntity.setId(id);
        supplierEntity.setState(existingSupplier.getState());
        supplierRepository.save(supplierEntity);
    }
    @Transactional
    public void changeSupplierState(Integer id, String state) throws SupplierNotFoundException, StateNotFoundException {
        SupplierEntity supplierEntity = supplierRepository.findById(id).orElseThrow(SupplierNotFoundException::new);
        StateEntity stateEntity = stateRepository.findByName(state).orElseThrow(StateNotFoundException::new);
        supplierEntity.setState(stateEntity);
        supplierRepository.save(supplierEntity);
    }
}
