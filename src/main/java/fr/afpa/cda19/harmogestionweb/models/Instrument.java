package fr.afpa.cda19.harmogestionweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modèle représentant un instrument.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrument {

    private Integer idInstrument;
    private String libelleInstrument;
}
