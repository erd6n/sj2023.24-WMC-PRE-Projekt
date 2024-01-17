package at.spengergasse.sj202324preauditverwaltung.controller;


import at.spengergasse.sj202324preauditverwaltung.model.Law;
import at.spengergasse.sj202324preauditverwaltung.model.LawTypes;
import at.spengergasse.sj202324preauditverwaltung.repository.LawRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/laws") // Verwenden Sie den Plural "laws" für die Basisklasse, um alle Gesetze darzustellen
@CrossOrigin("http://localhost:5173/")
public class LawController {
    @Autowired
    private LawRepository lawRepository;


    @GetMapping("/") // Verwenden Sie "/" um alle Gesetze abzurufen
    public List<Law> fetchLaws() {
        return lawRepository.findAll();
    }

    @GetMapping("/{id}") // Verwenden Sie "/{id}" um ein einzelnes Gesetz abzurufen
    public Law getLawById(@PathVariable Long id) {
        return lawRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Law not found with id " + id));
    }

    @GetMapping("/types")
    public List<LawTypes> getTyps() {
        return Arrays.stream(LawTypes.values()).toList();
    }

    @PostMapping("/") // Verwenden Sie "/" um ein neues Gesetz hinzuzufügen
    public Law newLaw(@Valid @RequestBody Law newLaw) {
        return lawRepository.save(newLaw);
    }

    @DeleteMapping("/{id}") // Verwenden Sie "/{id}" um ein Gesetz zu löschen
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
                    return lawRepository.save(law);
                })
                .orElseGet(() -> {
                    return lawRepository.save(updatedLaw);
                });
    }
}

