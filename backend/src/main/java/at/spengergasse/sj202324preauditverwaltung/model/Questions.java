package at.spengergasse.sj202324preauditverwaltung.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Setter
@Builder
@Table(name = "questions")
public class Questions extends AbstractPersistable<Long> {
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Audit> q_a_audits;

    @ManyToOne()
    private Law q_l_lawId;

    private boolean q_audited;
    private int q_finding_level;

    public boolean getAudited() {
        return this.q_audited;
    }
}
