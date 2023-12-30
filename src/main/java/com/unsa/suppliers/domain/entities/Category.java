package com.unsa.suppliers.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "categories")
@Entity(name = "category")
@Setter @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;
}
