package at.spengergasse.sj202324preauditverwaltung.repository;

import at.spengergasse.sj202324preauditverwaltung.model.AuditQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditQuestionRepository extends JpaRepository<AuditQuestion, Long> {
}
