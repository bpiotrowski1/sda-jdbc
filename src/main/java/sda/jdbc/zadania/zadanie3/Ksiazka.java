package sda.jdbc.zadania.zadanie3;

import lombok.Getter;
import lombok.Setter;

class Ksiazka {
    @Getter @Setter private Integer id;
    @Getter @Setter private String tytul;

    Ksiazka(final Integer id, final String tytul) {
        this.id = id;
        this.tytul = tytul;
    }
}
