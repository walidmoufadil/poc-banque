package org.example.virementservice.client;

import org.example.virementservice.dto.BeneficiaireDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "beneficiaire-service", url = "http://localhost:9000")
public interface BeneficiaireClient {
    @GetMapping("/api/beneficiaires/{id}")
    BeneficiaireDto getBeneficiaireById(@PathVariable("id") Long id);
}

