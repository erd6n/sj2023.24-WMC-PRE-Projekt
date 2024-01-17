package at.spengergasse.sj202324preauditverwaltung.repository;

import at.spengergasse.sj202324preauditverwaltung.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
