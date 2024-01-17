package at.spengergasse.sj202324preauditverwaltung.controller;

import at.spengergasse.sj202324preauditverwaltung.model.Audit;
import at.spengergasse.sj202324preauditverwaltung.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
@CrossOrigin("http://localhost:5173/")
public class AuditController {
    @Autowired
    private AuditRepository auditRepository;

    @GetMapping("/")
    public List<Audit> fetchAudits() {
        return auditRepository.findAll();
    }

    @GetMapping("/{id}")
    public Audit getAuditById(@PathVariable Long id) {
        return auditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit not found with id " + id));
    }

    @PostMapping("/")
    public Audit newAudit(@RequestBody Audit newAudit) {
        return auditRepository.save(newAudit);
    }

    @DeleteMapping("/{id}")
    public void deleteAuditById(@PathVariable Long id) {
        auditRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Audit updateAudit(@RequestBody Audit updatedAudit, @PathVariable Long id) {
        return auditRepository.findById(id)
                .map(audit -> {
                    audit.setA_auditDatum(updatedAudit.getA_auditDatum());
                    audit.setA_leadAuditor(updatedAudit.getA_leadAuditor());
                    audit.setA_leadAuditee(updatedAudit.getA_leadAuditee());
                    audit.setA_auditStatus(updatedAudit.getA_auditStatus());
                    audit.setA_ort(updatedAudit.getA_ort());
                    audit.setA_thema(updatedAudit.getA_thema());
                    audit.setA_typ(updatedAudit.getA_typ());
                    audit.setA_q_questions(updatedAudit.getA_q_questions());
                    audit.setA_anzTage(updatedAudit.getA_anzTage());
                    return auditRepository.save(audit);
                })
                .orElseGet(() -> {
                    return auditRepository.save(updatedAudit);
                });
    }
}