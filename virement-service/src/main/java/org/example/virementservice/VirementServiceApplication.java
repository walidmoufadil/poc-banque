package org.example.virementservice;

import org.example.virementservice.entity.Virement;
import org.example.virementservice.repository.VirementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
@EnableFeignClients
public class VirementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirementServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(VirementRepository virementRepository) {
        return args -> {
            virementRepository.saveAll(
                    List.of(
                            Virement.builder()
                                    .montant(1500.0)
                                            .ribSource("FR7612345987650123456789015")
                                                    .beneficiaireId(1L)
                                                            .dateVirement(LocalDateTime.now())
                                                                    .build()
                    )
            );
        };
    }
}

