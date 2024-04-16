package at.spengergasse.sj202324preauditverwaltung.controller;


import at.spengergasse.sj202324preauditverwaltung.model.Law;
import at.spengergasse.sj202324preauditverwaltung.model.LawTypes;
import at.spengergasse.sj202324preauditverwaltung.repository.LawRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/laws") // Verwenden Sie den Plural "laws" für die Basisklasse, um alle Gesetze darzustellen
@CrossOrigin("http://localhost:5173/")
public class LawController {
    @Autowired
    private LawRepository lawRepository;

    @GetMapping("/")
    public List<Law> fetchLaws() {
        return lawRepository.findAll();
    }

    @GetMapping("/{id}")
    public Law getLawById(@PathVariable Long id) {
        return lawRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Law not found with id " + id));
    }

    @GetMapping("/types")
    public List<LawTypes> getTyps() {
        return Arrays.stream(LawTypes.values()).toList();
    }

    @PostMapping("/")
    public Law newLaw(@Valid @RequestBody Law newLaw) {
        return lawRepository.save(newLaw);
    }

    @DeleteMapping("/{id}")
    public void deleteLawById(@PathVariable Long id) {
        lawRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Law updateLaw(@Valid @RequestBody Law updatedLaw, @PathVariable Long id) {
        return lawRepository.findById(id)
                .map(law -> {
                    law.setL_gesetz(updatedLaw.getL_gesetz());
                    law.setL_typ(updatedLaw.getL_typ());
                    law.setL_bezeichnung(updatedLaw.getL_bezeichnung());
                    law.setL_text(updatedLaw.getL_text());
                    law.setL_gueltigAb(updatedLaw.getL_gueltigAb());
                    law.setL_q_questions(updatedLaw.getL_q_questions());
                    return lawRepository.save(law);
                })
                .orElseGet(() -> lawRepository.save(updatedLaw));
    }
}

