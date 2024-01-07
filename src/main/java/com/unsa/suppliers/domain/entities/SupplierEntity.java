package com.unsa.suppliers.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "suppliers")
@Entity(name = "supplier")
@Setter @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String ruc;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;
}
