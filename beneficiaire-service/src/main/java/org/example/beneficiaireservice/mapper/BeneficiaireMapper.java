package org.example.beneficiaireservice.mapper;

import org.example.beneficiaireservice.dto.BeneficiaireRequestDto;
import org.example.beneficiaireservice.dto.BeneficiaireResponseDto;
import org.example.beneficiaireservice.entity.Beneficiaire;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaireMapper {
    ModelMapper mapper = new ModelMapper();
    public BeneficiaireResponseDto toDto(Beneficiaire beneficiaire){
        return mapper.map(beneficiaire, BeneficiaireResponseDto.class);
    }
    public Beneficiaire toEntity(BeneficiaireRequestDto dto){
        return mapper.map(dto, Beneficiaire.class);
    }
}
