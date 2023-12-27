package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.RoleEntity;
import com.unsa.suppliers.domain.exceptions.roles.RoleDuplicatedException;
import com.unsa.suppliers.domain.exceptions.roles.RoleNotFoundException;
import com.unsa.suppliers.domain.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }
    public RoleEntity getRoleById(Integer id) throws RoleNotFoundException {
        Optional<RoleEntity> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty()) { throw new RoleNotFoundException(); }
        return optionalRole.get();
    }
    @Transactional
    public RoleEntity createRole(RoleEntity roleEntity) throws RoleDuplicatedException {
        Optional<RoleEntity> optionalRole = roleRepository.findByName(roleEntity.getName());
        if (optionalRole.isPresent()) { throw new RoleDuplicatedException(); }
        return roleRepository.save(roleEntity);
    }
    @Transactional
    public void updateRole(Integer id, RoleEntity roleEntity) throws RoleNotFoundException, RoleDuplicatedException {
        Optional<RoleEntity> optionalRoleId = roleRepository.findById(id);
        if (optionalRoleId.isEmpty()) { throw new RoleNotFoundException(); }
        Optional<RoleEntity> optionalRoleName = roleRepository.findByName(roleEntity.getName());
        if (optionalRoleName.isPresent()) { throw new RoleDuplicatedException(); }
        roleEntity.setId(id);
        roleRepository.save(roleEntity);
    }
    @Transactional
    public void deleteRole(Integer id) throws RoleNotFoundException {
        Optional<RoleEntity> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty()) { throw new RoleNotFoundException(); }
        roleRepository.deleteById(id);
    }
}
