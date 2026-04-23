package fr.afpa.cda19.harmogestionweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Modèle représentant une représentation musicale.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Representation {

    private Integer idRepresentation;
    private String nomRepresentation;
    private LocalDateTime dateRepresentation;
    private String lieuRepresentation;
    private List<Membre> listeMembres = new ArrayList<>();
    private List<Instrument> listeInstruments = new ArrayList<>();
}
