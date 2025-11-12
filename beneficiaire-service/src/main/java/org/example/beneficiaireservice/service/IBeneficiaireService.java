package org.example.beneficiaireservice.service;

import org.example.beneficiaireservice.dto.BeneficiaireRequestDto;
import org.example.beneficiaireservice.dto.BeneficiaireResponseDto;

import java.util.List;

public interface IBeneficiaireService {
    void save(BeneficiaireRequestDto beneficiaireRequestDto);
    void update(long id, BeneficiaireRequestDto beneficiaireRequestDto);
    void delete(Long id);
    List<BeneficiaireResponseDto> findAll();
    BeneficiaireResponseDto findById(Long id);
}
