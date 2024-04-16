package at.spengergasse.sj202324preauditverwaltung.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
    private Date a_auditDatum;
    private String a_leadAuditor;
    private String a_leadAuditee;
    private String a_auditStatus;
    private String a_ort;
    private String a_thema;
    private String a_typ;

    @JsonManagedReference
    //@OneToMany(mappedBy = "audit")
    @OneToMany(mappedBy = "audit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuditQuestion> auditQuestions;

    private int a_anzTage;
}