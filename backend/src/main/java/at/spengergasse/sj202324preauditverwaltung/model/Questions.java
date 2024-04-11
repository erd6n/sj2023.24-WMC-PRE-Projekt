package at.spengergasse.sj202324preauditverwaltung.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Setter
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Questions extends AbstractPersistable<Long> {
    @OneToMany(mappedBy = "question")
    private List<AuditQuestion> auditQuestions;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne()
    private Law q_l_law;

    private boolean q_audited;
    private int q_finding_level;

    public boolean getAudited() {
        return this.q_audited;
    }
}