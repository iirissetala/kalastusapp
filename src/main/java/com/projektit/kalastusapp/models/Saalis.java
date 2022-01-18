package com.projektit.kalastusapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SAALIS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class Saalis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    private String kalastaja;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
    private LocalDate pvm;
    private String paikka;

    @OneToMany(mappedBy = "saalis", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("saalis")
    private List<Kala> kalat = new ArrayList<>();

    private boolean poisto = false;
}
