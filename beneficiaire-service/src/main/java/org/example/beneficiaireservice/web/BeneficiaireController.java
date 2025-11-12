package org.example.beneficiaireservice.web;

import org.example.beneficiaireservice.dto.BeneficiaireRequestDto;
import org.example.beneficiaireservice.dto.BeneficiaireResponseDto;
import org.example.beneficiaireservice.service.IBeneficiaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaires")
public class BeneficiaireController {
    private IBeneficiaireService beneficiaireService;

    public BeneficiaireController(IBeneficiaireService beneficiaireService) {
        this.beneficiaireService = beneficiaireService;
    }
    @GetMapping()
    public List<BeneficiaireResponseDto> getAllBeneficiaires() {
        return beneficiaireService.findAll();
    }
    @GetMapping("/{id}")
    public BeneficiaireResponseDto getBeneficiaireById(@PathVariable Long id) {
        return beneficiaireService.findById(id);
    }
    @PostMapping()
    public void createBeneficiaire(@RequestBody BeneficiaireRequestDto beneficiaireRequestDto) {
        beneficiaireService.save(beneficiaireRequestDto);
    }
    @PutMapping
    public void updateBeneficiaire(long id, @RequestBody BeneficiaireRequestDto beneficiaireRequestDto) {
        beneficiaireService.update(id, beneficiaireRequestDto);
    }
    @DeleteMapping("/{id}")
    public void deleteBeneficiaire(@PathVariable Long id) {
        beneficiaireService.delete(id);
    }

}
