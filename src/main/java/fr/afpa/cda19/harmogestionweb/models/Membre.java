package fr.afpa.cda19.harmogestionweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Modèle représentant un membre.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membre {

    private Integer idMembre;
    private String nomMembre;
    private String prenomMembre;
    private LocalDate dateInscriptionMembre;
}
