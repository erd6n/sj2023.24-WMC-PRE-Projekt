package at.spengergasse.sj202324preauditverwaltung.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Setter
@Builder
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "q_l_lawId")
    private List<Questions> l_q_questions;


}

