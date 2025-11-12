package org.example.ragservice;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class RagServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RagServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(VectorStore vectorStore) {
        return args -> {
            try {
                // Lire le fichier PDF avec PagePdfDocumentReader
                PagePdfDocumentReader pdfDocumentReader = new PagePdfDocumentReader(
                        new ClassPathResource("MOUFADIL_WALID_DEVELOPPEUR_LOGICIEL.pdf")
                );
                List<Document> documents = pdfDocumentReader.get();

                // Diviser le contenu en morceaux
                TextSplitter textSplitter = new TokenTextSplitter();
                List<Document> chunks = textSplitter.split(documents);

                // Ajouter les morceaux au Vector Store
                vectorStore.add(chunks);

            } catch (Exception e) {
                throw new RuntimeException("Erreur lors du traitement du fichier PDF : " + e.getMessage(), e);
            }
        };
    }

}
