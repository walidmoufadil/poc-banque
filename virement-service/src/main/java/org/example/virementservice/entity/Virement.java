package org.example.virementservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.virementservice.model.Beneficiaire;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "virements")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long beneficiaireId;

    private String ribSource;

    private double montant;

    private String description;

    private LocalDateTime dateVirement;

    @Enumerated(EnumType.STRING)
    private TypeVirement type;

    @Transient
    private Beneficiaire beneficiaire;
}

