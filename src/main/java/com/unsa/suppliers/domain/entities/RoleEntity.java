package com.unsa.suppliers.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "roles")
@Entity(name = "role")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
