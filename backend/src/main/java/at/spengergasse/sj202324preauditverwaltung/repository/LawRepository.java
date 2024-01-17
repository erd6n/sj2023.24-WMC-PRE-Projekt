package at.spengergasse.sj202324preauditverwaltung.repository;

import at.spengergasse.sj202324preauditverwaltung.model.Law;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawRepository extends JpaRepository<Law, Long> {

}
