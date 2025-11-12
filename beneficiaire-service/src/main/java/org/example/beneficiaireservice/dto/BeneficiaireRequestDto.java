package org.example.beneficiaireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.beneficiaireservice.entity.Type;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BeneficiaireRequestDto {
    private String nom;
    private String prenom;
    private String rib;
    private Type type;
}
