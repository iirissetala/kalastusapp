package com.projektit.kalastusapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity(name = "KALA")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class Kala {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    private String laji;
    private double pituus;
    private int paino;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SAALIS_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties("kalat")
    private Saalis saalis;

    public Kala(String laji, double pituus, int paino) {
        if (laji == null) {
            throw new IllegalArgumentException("Kalan laji on pakollinen tieto.");
        }
        this.laji = laji;
        this.pituus = pituus;
        this.paino = paino;
    }
}
