package com.unsa.suppliers.domain.entities;

import jakarta.persistence.*;

@Table(name = "countries")
@Entity(name = "country")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;
}
