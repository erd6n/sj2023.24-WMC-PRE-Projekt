package at.spengergasse.sj202324preauditverwaltung.controller;

import at.spengergasse.sj202324preauditverwaltung.model.AuditQuestion;
import at.spengergasse.sj202324preauditverwaltung.repository.AuditQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditQuestions")
@CrossOrigin("http://localhost:5173/")
public class AuditQuestionController {
    @Autowired
    private AuditQuestionRepository auditQuestionRepository;

    @GetMapping("/")
    public List<AuditQuestion> fetchAuditQuestions() {
        return auditQuestionRepository.findAll();
    }

    @GetMapping("/{id}")
    public AuditQuestion getAuditQuestionById(@PathVariable Long id) {
        return auditQuestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuditQuestion not found with id " + id));
    }

    @PostMapping("/")
    public AuditQuestion newAuditQuestion(@RequestBody AuditQuestion newAuditQuestion) {
        return auditQuestionRepository.save(newAuditQuestion);
    }

    @DeleteMapping("/{id}")
    public void deleteAuditQuestionById(@PathVariable Long id) {
        auditQuestionRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public AuditQuestion updateAuditQuestion(@RequestBody AuditQuestion updatedAuditQuestion, @PathVariable Long id) {
        return auditQuestionRepository.findById(id)
                .map(auditQuestion -> {
                    auditQuestion.setAudit(updatedAuditQuestion.getAudit());
                    auditQuestion.setQuestion(updatedAuditQuestion.getQuestion());
                    auditQuestion.setCreatedDate(updatedAuditQuestion.getCreatedDate());
                    return auditQuestionRepository.save(auditQuestion);
                })
                .orElseGet(() -> auditQuestionRepository.save(updatedAuditQuestion));
    }
}