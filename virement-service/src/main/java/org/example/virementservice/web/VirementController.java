package org.example.virementservice.web;

import org.example.virementservice.dto.VirementRequestDto;
import org.example.virementservice.dto.VirementResponseDto;
import org.example.virementservice.service.IVirementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/virements")
public class VirementController {

    private final IVirementService virementService;

    public VirementController(IVirementService virementService) {
        this.virementService = virementService;
    }

    @GetMapping
    public ResponseEntity<List<VirementResponseDto>> getAllVirements() {
        return ResponseEntity.ok(virementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VirementResponseDto> getVirementById(@PathVariable Long id) {
        return ResponseEntity.ok(virementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VirementResponseDto> createVirement(@RequestBody VirementRequestDto virementRequestDto) {
        VirementResponseDto created = virementService.save(virementRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VirementResponseDto> updateVirement(@PathVariable Long id,
                                                              @RequestBody VirementRequestDto virementRequestDto) {
        VirementResponseDto updated = virementService.update(id, virementRequestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVirement(@PathVariable Long id) {
        virementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/beneficiaire/{beneficiaireId}")
    public ResponseEntity<List<VirementResponseDto>> getVirementsByBeneficiaire(@PathVariable Long beneficiaireId) {
        return ResponseEntity.ok(virementService.findByBeneficiaireId(beneficiaireId));
    }

    @GetMapping("/rib/{ribSource}")
    public ResponseEntity<List<VirementResponseDto>> getVirementsByRibSource(@PathVariable String ribSource) {
        return ResponseEntity.ok(virementService.findByRibSource(ribSource));
    }
}

