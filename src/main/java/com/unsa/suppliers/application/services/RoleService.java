package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.RoleEntity;
import com.unsa.suppliers.domain.exceptions.roles.RoleDuplicatedException;
import com.unsa.suppliers.domain.exceptions.roles.RoleNotFoundException;
import com.unsa.suppliers.domain.exceptions.roles.RoleInUseException;
import com.unsa.suppliers.domain.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }
    public RoleEntity getRoleById(Integer id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }
    @Transactional
    public RoleEntity createRole(RoleEntity roleEntity) throws RoleDuplicatedException {
        if (roleRepository.existsByName(roleEntity.getName())) { throw new RoleDuplicatedException(); }
        return roleRepository.save(roleEntity);
    }
    @Transactional
    public void updateRole(Integer id, RoleEntity roleEntity) throws RoleNotFoundException, RoleDuplicatedException {
        RoleEntity existingRole = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
        if (!existingRole.getName().equals(roleEntity.getName()) && roleRepository.existsByName(roleEntity.getName())) {
            throw new RoleDuplicatedException();
        }
        existingRole.setName(roleEntity.getName());
        roleRepository.save(existingRole);
    }
    @Transactional
    public void deleteRole(Integer id) throws RoleNotFoundException, RoleInUseException {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
        switch (roleEntity.getName()) {
            case USER_ROLE, ADMIN_ROLE -> throw new RoleInUseException();
            default -> roleRepository.deleteById(id);
        }
    }
}
