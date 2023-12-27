package com.unsa.suppliers.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Table(name = "users")
@Entity(name = "user")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
}
