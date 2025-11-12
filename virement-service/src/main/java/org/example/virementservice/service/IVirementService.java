package org.example.virementservice.service;

import org.example.virementservice.dto.VirementRequestDto;
import org.example.virementservice.dto.VirementResponseDto;

import java.util.List;

public interface IVirementService {
    VirementResponseDto save(VirementRequestDto virementRequestDto);
    VirementResponseDto update(Long id, VirementRequestDto virementRequestDto);
    void delete(Long id);
    List<VirementResponseDto> findAll();
    VirementResponseDto findById(Long id);
    List<VirementResponseDto> findByBeneficiaireId(Long beneficiaireId);
    List<VirementResponseDto> findByRibSource(String ribSource);
}

