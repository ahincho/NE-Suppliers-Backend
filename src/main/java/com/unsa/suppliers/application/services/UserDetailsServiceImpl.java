package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.UserEntity;
import com.unsa.suppliers.domain.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with the username " + username + " does not exists"));
        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(roleEntity -> new SimpleGrantedAuthority("ROLE_" + roleEntity.getName())).collect(Collectors.toSet());
        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, authorities);
    }
}
