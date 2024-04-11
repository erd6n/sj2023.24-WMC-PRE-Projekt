package at.spengergasse.sj202324preauditverwaltung.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Setter
@Builder
public class AuditQuestion extends AbstractPersistable<Long> {
    @JsonBackReference
    @ManyToOne
    private Audit audit;

    @ManyToOne
    private Questions question;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}