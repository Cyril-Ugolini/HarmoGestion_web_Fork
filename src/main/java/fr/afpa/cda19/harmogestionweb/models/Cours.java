package fr.afpa.cda19.harmogestionweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Modèle représentant un cours.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cours {

    private Integer idCours;
    private LocalDateTime dateCours;
    private byte dureeCours;
    private Membre enseignant;
    private Instrument instrument;
    private List<Membre> participants = new ArrayList<>();
}
