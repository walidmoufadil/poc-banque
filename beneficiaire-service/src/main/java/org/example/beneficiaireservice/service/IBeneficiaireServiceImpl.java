package org.example.beneficiaireservice.service;

import org.example.beneficiaireservice.dto.BeneficiaireRequestDto;
import org.example.beneficiaireservice.dto.BeneficiaireResponseDto;
import org.example.beneficiaireservice.entity.Beneficiaire;
import org.example.beneficiaireservice.mapper.BeneficiaireMapper;
import org.example.beneficiaireservice.repository.BeneficiaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IBeneficiaireServiceImpl implements IBeneficiaireService {
    private BeneficiaireRepository beneficiaireRepository;
    private BeneficiaireMapper beneficiaireMapper;

    public IBeneficiaireServiceImpl(BeneficiaireRepository repository, BeneficiaireMapper beneficiaireMapper) {
        this.beneficiaireRepository = repository;
        this.beneficiaireMapper = beneficiaireMapper;
    }

    @Override
    public void save(BeneficiaireRequestDto beneficiaireRequestDto) {
        Beneficiaire entity = beneficiaireMapper.toEntity(beneficiaireRequestDto);
        beneficiaireRepository.save(entity);
    }

    @Override
    public void update(long id, BeneficiaireRequestDto beneficiaireRequestDto) {
        Beneficiaire beneficiaire = beneficiaireRepository.findById(id).orElseThrow(()-> new RuntimeException("Beneficiaire not found"));
        if(beneficiaireRequestDto.getNom() != null) beneficiaire.setNom(beneficiaireRequestDto.getNom());
        if(beneficiaireRequestDto.getPrenom() != null) beneficiaire.setPrenom(beneficiaireRequestDto.getPrenom());
        if(beneficiaireRequestDto.getRib() != null) beneficiaire.setRib(beneficiaireRequestDto.getRib());
        if(beneficiaireRequestDto.getType() != null) beneficiaire.setType(beneficiaireRequestDto.getType());
        beneficiaireRepository.save(beneficiaire);
    }

    @Override
    public void delete(Long id) {
        beneficiaireRepository.deleteById(id);
    }

    @Override
    public List<BeneficiaireResponseDto> findAll() {
        return beneficiaireRepository.findAll().stream().map(beneficiaireMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BeneficiaireResponseDto findById(Long id) {
        return beneficiaireMapper.toDto(beneficiaireRepository.findById(id).orElseThrow(()-> new RuntimeException("Beneficiaire not found")));
    }
}
