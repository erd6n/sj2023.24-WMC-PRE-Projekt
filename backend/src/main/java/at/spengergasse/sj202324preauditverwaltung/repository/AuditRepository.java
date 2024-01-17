package at.spengergasse.sj202324preauditverwaltung.repository;

import at.spengergasse.sj202324preauditverwaltung.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> {
    //Die JPA Repository gibt uns die CRUD Methoden vor, damit wir diese durchführen können
}
