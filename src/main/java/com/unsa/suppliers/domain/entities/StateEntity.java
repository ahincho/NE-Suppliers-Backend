package com.unsa.suppliers.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "states")
@Entity(name = "state")
@Setter @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
