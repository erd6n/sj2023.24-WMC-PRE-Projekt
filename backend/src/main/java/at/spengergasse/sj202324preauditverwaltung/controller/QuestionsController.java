package at.spengergasse.sj202324preauditverwaltung.controller;

import at.spengergasse.sj202324preauditverwaltung.model.Questions;
import at.spengergasse.sj202324preauditverwaltung.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin("http://localhost:5173/")
public class QuestionsController {
    @Autowired
    private QuestionsRepository questionsRepository;

    @GetMapping("/")
    public List<Questions> fetchQuestions() {
        return questionsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Questions getQuestionsById(@PathVariable Long id) {
        return questionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Questions not found with id " + id));
    }

    @PostMapping("/")
    public Questions newQuestions(@RequestBody Questions newQuestions) {
        return questionsRepository.save(newQuestions);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestionsById(@PathVariable Long id) {
        questionsRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Questions updateQuestions(@RequestBody Questions updatedQuestions, @PathVariable Long id) {
        return questionsRepository.findById(id)
                .map(questions -> {
                    questions.setQ_finding_level(updatedQuestions.getQ_finding_level());
                    questions.setQ_audited(updatedQuestions.getAudited());
                    return questionsRepository.save(questions);
                })
                .orElseGet(() -> {
                    return questionsRepository.save(updatedQuestions);
                });
    }
}