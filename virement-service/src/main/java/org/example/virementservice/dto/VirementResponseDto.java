package org.example.virementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.virementservice.entity.TypeVirement;
import org.example.virementservice.model.Beneficiaire;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VirementResponseDto {
    private Long id;
    private Long beneficiaireId;
    private String ribSource;
    private double montant;
    private String description;
    private LocalDateTime dateVirement;
    private TypeVirement type;
    private BeneficiaireDto beneficiaire;
}

