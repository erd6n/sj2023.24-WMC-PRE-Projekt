package at.spengergasse.sj202324preauditverwaltung;

import at.spengergasse.sj202324preauditverwaltung.controller.ResourceNotFoundException;
import at.spengergasse.sj202324preauditverwaltung.model.Law;
import at.spengergasse.sj202324preauditverwaltung.model.LawTypes;
import at.spengergasse.sj202324preauditverwaltung.model.Questions;
import at.spengergasse.sj202324preauditverwaltung.repository.AuditRepository;
import at.spengergasse.sj202324preauditverwaltung.repository.LawRepository;
import at.spengergasse.sj202324preauditverwaltung.repository.QuestionsRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Sj202324PreAuditVerwaltungApplication implements CommandLineRunner {

    public static void main(String[] args) {
            SpringApplication.run(Sj202324PreAuditVerwaltungApplication.class, args);
    }

    @Autowired
    private LawRepository lawRepository;
    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private AuditRepository auditsRepository;
    Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 27; i++) {
            String typ = "";
            String bezeichnung = "";
            LawTypes lawType;
            int rand = random.nextInt(3);

            if (rand == 0) {
                lawType = LawTypes.AMC;
                bezeichnung = "AMC1.CAMO.A.100";
            } else if (rand == 1) {
                lawType = LawTypes.GM;
                bezeichnung = "GM1.CAMO.A.100";
            } else {
                lawType = LawTypes.R;
                bezeichnung = "CAMO.A.100";
            }
            lawRepository.save(
                    Law.builder()
                    .l_gesetz("PART.CAMO")
                    .l_typ(lawType)
                    .l_bezeichnung(bezeichnung)
                    .l_text("Lore ipsum …")
                    .l_gueltigAb("2024-01-01")
                    .build()
            );
        }
    }
}
