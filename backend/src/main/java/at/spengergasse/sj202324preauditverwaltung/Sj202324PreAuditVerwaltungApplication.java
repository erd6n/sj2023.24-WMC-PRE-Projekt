package at.spengergasse.sj202324preauditverwaltung;

import at.spengergasse.sj202324preauditverwaltung.model.*;
import at.spengergasse.sj202324preauditverwaltung.repository.AuditQuestionRepository;
import at.spengergasse.sj202324preauditverwaltung.repository.AuditRepository;
import at.spengergasse.sj202324preauditverwaltung.repository.LawRepository;
import at.spengergasse.sj202324preauditverwaltung.repository.QuestionsRepository;
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
    @Autowired
    private AuditQuestionRepository auditQuestionRepository;
    Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Law law = Law.builder()
                    .l_gesetz("Gesetz " + i)
                    .l_typ(LawTypes.values()[random.nextInt(LawTypes.values().length)])
                    .l_bezeichnung("Bezeichnung " + i)
                    .l_text("Text " + i)
                    .l_gueltigAb("2024-01-01")
                    .build();
            lawRepository.save(law);

            List<AuditQuestion> auditQuestions = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Questions question = Questions.builder()
                        .q_l_law(law)
                        .q_finding_level(random.nextInt(4))
                        .q_audited(random.nextBoolean())
                        .build();
                questionsRepository.save(question);

                AuditQuestion auditQuestion = AuditQuestion.builder()
                        .audit(null)
                        .question(question)
                        .createdDate(new Date())
                        .build();
                auditQuestions.add(auditQuestion);
            }

            Audit audit = Audit.builder()
                    .a_auditDatum(new Date())
                    .a_leadAuditor("Lead Auditor " + i)
                    .a_leadAuditee("Lead Auditee " + i)
                    .a_auditStatus("Audit Status " + i)
                    .a_ort("Ort " + i)
                    .a_thema("Thema " + i)
                    .a_typ("Typ " + i)
                    .auditQuestions(auditQuestions)
                    .a_anzTage(random.nextInt(10))
                    .build();

            auditsRepository.save(audit);

            for (AuditQuestion auditQuestion : auditQuestions) {
                auditQuestion.setAudit(audit);
                auditQuestionRepository.save(auditQuestion);
            }
        }
    }
}

