package com.projektit.kalastusapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "SAALIS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Saalis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date pvm;
    private String paikka;
    @OneToMany(mappedBy = "saalis", cascade = CascadeType.ALL)
    private List<Kala> kalat;
}
