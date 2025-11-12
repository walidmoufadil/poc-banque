package org.example.beneficiaireservice.repository;

import org.example.beneficiaireservice.entity.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire ,Long> {
}
