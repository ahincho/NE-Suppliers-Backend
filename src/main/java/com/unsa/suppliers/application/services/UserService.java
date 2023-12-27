package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.RoleEntity;
import com.unsa.suppliers.domain.entities.UserEntity;
import com.unsa.suppliers.domain.exceptions.roles.RoleNotFoundException;
import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedEmailException;
import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedUsernameException;
import com.unsa.suppliers.domain.exceptions.users.UserNotFoundException;
import com.unsa.suppliers.domain.repositories.RoleRepository;
import com.unsa.suppliers.domain.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { throw new UserNotFoundException(); }
        return optionalUser.get();
    }
    @Transactional
    public UserEntity createUser(UserEntity userEntity) throws UserDuplicatedEmailException, UserDuplicatedUsernameException, RoleNotFoundException {
        Optional<UserEntity> optionalUserEmail = userRepository.findByEmail(userEntity.getEmail());
        if (optionalUserEmail.isPresent()) { throw new UserDuplicatedEmailException(); }
        Optional<UserEntity> optionalUserUsername = userRepository.findByUsername(userEntity.getUsername());
        if (optionalUserUsername.isPresent()) { throw new UserDuplicatedUsernameException(); }
        Optional<RoleEntity> roleEntity = roleRepository.findByName("USER");
        if (roleEntity.isEmpty()) { throw new RoleNotFoundException(); }
        userEntity.setRoles(Set.of(roleEntity.get()));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }
    @Transactional
    public void updateUser(Integer id, UserEntity userEntity) throws UserNotFoundException, UserDuplicatedUsernameException {
        Optional<UserEntity> optionalUserId = userRepository.findById(id);
        if (optionalUserId.isEmpty()) { throw new UserNotFoundException(); }
        Optional<UserEntity> optionalUserUsername = userRepository.findByUsername(userEntity.getUsername());
        if (optionalUserUsername.isPresent()) { throw new UserDuplicatedUsernameException(); }
        UserEntity savedUserEntity = optionalUserId.get();
        savedUserEntity.setName(userEntity.getName());
        savedUserEntity.setLastname(userEntity.getLastname());
        savedUserEntity.setUsername(userEntity.getUsername());
        userRepository.save(savedUserEntity);
    }
    @Transactional
    public void deleteUser(Integer id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { throw new UserNotFoundException(); }
        userRepository.deleteById(id);
    }
}
