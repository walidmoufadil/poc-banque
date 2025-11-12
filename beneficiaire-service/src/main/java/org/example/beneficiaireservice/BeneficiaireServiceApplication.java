package org.example.beneficiaireservice;

import org.example.beneficiaireservice.entity.Beneficiaire;
import org.example.beneficiaireservice.repository.BeneficiaireRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BeneficiaireServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeneficiaireServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BeneficiaireRepository beneficiaireRepository) {
        return args -> {
            beneficiaireRepository.saveAll(
                    List.of(
                            Beneficiaire.builder()
                                    .nom("Smith")
                                    .prenom("Alice")
                                    .rib("FR7612345987650123456789014")
                                    .build(),
                            Beneficiaire.builder()
                                    .nom("Johnson")
                                    .prenom("Bob")
                                    .rib("FR7612345987650123456789015")
                                    .build()
                    )
            );
        };
    }

}
