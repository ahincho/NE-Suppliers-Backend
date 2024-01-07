package com.unsa.suppliers.application.services;

import static com.unsa.suppliers.application.services.RoleService.USER_ROLE;

import com.unsa.suppliers.domain.entities.RoleEntity;
import com.unsa.suppliers.domain.entities.UserEntity;
import com.unsa.suppliers.domain.exceptions.roles.RoleNotFoundException;
import com.unsa.suppliers.domain.exceptions.users.*;
import com.unsa.suppliers.domain.repositories.RoleRepository;
import com.unsa.suppliers.domain.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public UserEntity findUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    @Transactional
    public UserEntity createUser(UserEntity userEntity) throws UserDuplicatedEmailException, UserDuplicatedUsernameException, RoleNotFoundException {
        if (userRepository.existsByEmail(userEntity.getEmail())) { throw new UserDuplicatedEmailException(); }
        if (userRepository.existsByUsername(userEntity.getUsername())) { throw new UserDuplicatedUsernameException(); }
        RoleEntity defaultRole = roleRepository.findByName(USER_ROLE).orElseThrow(RoleNotFoundException::new);
        userEntity.setRoles(Set.of(defaultRole));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }
    @Transactional
    public void updateUser(Integer id, UserEntity userEntity) throws UserNotFoundException, UserDuplicatedUsernameException, UserDuplicatedEmailException {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (!existingUser.getUsername().equals(userEntity.getUsername()) && userRepository.existsByUsername(userEntity.getUsername())) {
            throw new UserDuplicatedUsernameException();
        }
        if (!existingUser.getEmail().equals(userEntity.getEmail()) && userRepository.existsByEmail(userEntity.getEmail())) {
            throw new UserDuplicatedEmailException();
        }
        existingUser.setName(userEntity.getName());
        existingUser.setLastname(userEntity.getLastname());
        existingUser.setUsername(userEntity.getUsername());
        existingUser.setEmail(userEntity.getEmail());
        userRepository.save(existingUser);
    }
    @Transactional
    public void deleteUser(Integer id) throws UserNotFoundException {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }
}
