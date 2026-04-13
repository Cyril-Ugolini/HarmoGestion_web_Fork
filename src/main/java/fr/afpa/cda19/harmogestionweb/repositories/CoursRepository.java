package fr.afpa.cda19.harmogestionweb.repositories;

import fr.afpa.cda19.harmogestionweb.models.Cours;
import fr.afpa.cda19.harmogestionweb.models.Instrument;
import fr.afpa.cda19.harmogestionweb.models.Membre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Repository des cours.
 *
 * @author Seiwert Thomas
 * @version 0.0.1
 * @since 10/04/2026
 */
@Component
@Slf4j
public class CoursRepository {

    public Iterable<Cours> getAllCours() {

        ArrayList<Cours> listeCours = new ArrayList<>();
        ArrayList<Membre> participants = new ArrayList<>();
        participants.add(new Membre(2, "Seiwert",
                                    "Thomas", LocalDate.now()));
        participants.add(new Membre(3, "Didier",
                                    "Cédric", LocalDate.now()));
        listeCours.add(new Cours(1, LocalDateTime.now().plusDays(2), (byte) 45,
                                 new Membre(1, "Hendrix",
                                            "Jimmi", LocalDate.now()),
                                 new Instrument(1, "guitare"),
                                 participants));
        listeCours.add(new Cours(2, LocalDateTime.now().plusDays(3), (byte) 45,
                                 new Membre(1, "Hendrix",
                                            "Jimmi", LocalDate.now()),
                                 new Instrument(1, "guitare"),
                                 participants));
        listeCours.add(new Cours(3, LocalDateTime.now().plusDays(1), (byte) 45,
                                 new Membre(1, "Hendrix",
                                            "Jimmi", LocalDate.now()),
                                 new Instrument(1, "guitare"),
                                 participants));

        return listeCours;
    }

    public Cours getCours(final int id) {

        return new Cours();
    }

    public Cours createCours(final Cours cours) {

        return new Cours();
    }

    public Cours updateCours(final Cours cours) {

        return new Cours();
    }

    public void deleteCours(final int id) {
    }
}
