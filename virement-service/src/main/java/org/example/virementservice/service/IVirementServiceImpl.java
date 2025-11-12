package org.example.virementservice.service;

import org.example.virementservice.client.BeneficiaireClient;
import org.example.virementservice.dto.VirementRequestDto;
import org.example.virementservice.dto.VirementResponseDto;
import org.example.virementservice.entity.Virement;
import org.example.virementservice.model.Beneficiaire;
import org.example.virementservice.repository.VirementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IVirementServiceImpl implements IVirementService {

    private final VirementRepository virementRepository;
    private final BeneficiaireClient beneficiaireClient;

    public IVirementServiceImpl(VirementRepository virementRepository, BeneficiaireClient beneficiaireClient) {
        this.virementRepository = virementRepository;
        this.beneficiaireClient = beneficiaireClient;
    }

    @Override
    public VirementResponseDto save(VirementRequestDto virementRequestDto) {
        // Vérifier que le bénéficiaire existe via OpenFeign
        beneficiaireClient.getBeneficiaireById(virementRequestDto.getBeneficiaireId());

        Virement virement = Virement.builder()
                .beneficiaireId(virementRequestDto.getBeneficiaireId())
                .ribSource(virementRequestDto.getRibSource())
                .montant(virementRequestDto.getMontant())
                .description(virementRequestDto.getDescription())
                .dateVirement(virementRequestDto.getDateVirement())
                .type(virementRequestDto.getType())
                .build();

        Virement savedVirement = virementRepository.save(virement);
        return convertToResponseDto(savedVirement);
    }

    @Override
    public VirementResponseDto update(Long id, VirementRequestDto virementRequestDto) {
        Virement virement = virementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Virement not found with id: " + id));

        // Vérifier que le bénéficiaire existe via OpenFeign
        beneficiaireClient.getBeneficiaireById(virementRequestDto.getBeneficiaireId());

        virement.setBeneficiaireId(virementRequestDto.getBeneficiaireId());
        virement.setRibSource(virementRequestDto.getRibSource());
        virement.setMontant(virementRequestDto.getMontant());
        virement.setDescription(virementRequestDto.getDescription());
        virement.setDateVirement(virementRequestDto.getDateVirement());
        virement.setType(virementRequestDto.getType());

        Virement updatedVirement = virementRepository.save(virement);
        return convertToResponseDto(updatedVirement);
    }

    @Override
    public void delete(Long id) {
        if (!virementRepository.existsById(id)) {
            throw new RuntimeException("Virement not found with id: " + id);
        }
        virementRepository.deleteById(id);
    }

    @Override
    public List<VirementResponseDto> findAll() {

       return virementRepository.findAll()
               .stream()
               .map(virement -> {
                    VirementResponseDto virementResponseDto = convertToResponseDto(virement);
                    if (virementResponseDto.getBeneficiaireId() != null)
                        virementResponseDto.setBeneficiaire(beneficiaireClient.getBeneficiaireById(virement.getBeneficiaireId()));
                    return virementResponseDto;
                }).collect(Collectors.toList());
    }

    @Override
    public VirementResponseDto findById(Long id) {
        Virement virement = virementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Virement not found with id: " + id));
        VirementResponseDto virementResponseDto = convertToResponseDto(virement);
        if (virementResponseDto.getBeneficiaireId() != null)
            virementResponseDto.setBeneficiaire(beneficiaireClient.getBeneficiaireById(virement.getBeneficiaireId()));
        return virementResponseDto;
    }

    @Override
    public List<VirementResponseDto> findByBeneficiaireId(Long beneficiaireId) {
        return virementRepository.findByBeneficiaireId(beneficiaireId)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VirementResponseDto> findByRibSource(String ribSource) {
        return virementRepository.findByRibSource(ribSource)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private VirementResponseDto convertToResponseDto(Virement virement) {
        return VirementResponseDto.builder()
                .id(virement.getId())
                .beneficiaireId(virement.getBeneficiaireId())
                .ribSource(virement.getRibSource())
                .montant(virement.getMontant())
                .description(virement.getDescription())
                .dateVirement(virement.getDateVirement())
                .type(virement.getType())
                .build();
    }
}

