package at.spengergasse.sj202324preauditverwaltung.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Setter
@Builder
@Table(name = "law")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Law extends AbstractPersistable<Long> {
    @NotBlank(message = "Gesetz darf nicht leer sein")
    private String l_gesetz;

    @NotNull(message = "Typ darf nicht null sein")
    private LawTypes l_typ;

    @NotBlank(message = "Bezeichnung darf nicht leer sein")
    private String l_bezeichnung;

    @NotBlank(message = "Text darf nicht leer sein")
    private String l_text;

    @NotBlank(message = "Gültig Ab darf nicht leer sein")
    private String l_gueltigAb;

    @JsonManagedReference
    @OneToMany(mappedBy = "q_l_law")
    private List<Questions> l_q_questions;
}