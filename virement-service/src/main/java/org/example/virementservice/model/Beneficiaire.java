package org.example.virementservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiaire {
    private Long id;
    private String nom;
    private String prenom;
    private String rib;
    private String type;
}
