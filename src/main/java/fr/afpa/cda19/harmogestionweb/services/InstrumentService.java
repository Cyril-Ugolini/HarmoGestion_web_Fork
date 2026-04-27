package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Instrument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Service gérant les appels à l'API pour les instruments.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Service
public class InstrumentService {

    /**
     * URL de base de l'API.
     */
    @Value("${api.base.url}")
    private String apiBaseUrl;

    /**
     * Client REST pour les appels à l'API.
     */
    private final RestClient restClient;

    /**
     * Constructeur du service des instruments.
     */
    public InstrumentService() {
        this.restClient = RestClient.create();
    }

    /**
     * Récupère tous les instruments depuis l'API.
     *
     * @return la liste de tous les instruments
     */
    public List<Instrument> getAllInstruments() {
        return restClient.get()
                .uri(apiBaseUrl + "/instruments")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Instrument>>() {});
    }

    /**
     * Crée un nouvel instrument via l'API.
     *
     * @param instrument l'instrument à créer
     * @return l'instrument créé
     */
    public Instrument saveInstrument(final Instrument instrument) {
        return restClient.post()
                .uri(apiBaseUrl + "/instruments")
                .body(instrument)
                .retrieve()
                .body(Instrument.class);
    }
}
