package at.spengergasse.sj202324preauditverwaltung.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Setter
@Builder
public class Audit extends AbstractPersistable<Long> {
    @Column(name="a_auditDatum")
    private Date a_auditDatum;
    private String a_leadAuditor;
    private String a_leadAuditee;
    private String a_auditStatus;
    private String a_ort;
    private String a_thema;
    private String a_typ;

    @ManyToMany(mappedBy = "q_a_audits")
    private List<Questions> a_q_questions;


    private int a_anzTage;

}
