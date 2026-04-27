package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Representation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Service gérant les appels à l'API pour les représentations.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Service
public class RepresentationService {

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
     * Constructeur du service des représentations.
     */
    public RepresentationService() {
        this.restClient = RestClient.create();
    }

    /**
     * Récupère toutes les représentations depuis l'API.
     *
     * @return la liste de toutes les représentations
     */
    public List<Representation> getAllRepresentations() {
        return restClient.get()
                .uri(apiBaseUrl + "/representations")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Representation>>() {});
    }

    /**
     * Récupère une représentation par son identifiant depuis l'API.
     *
     * @param id l'identifiant de la représentation
     * @return la représentation correspondante
     */
    public Representation getRepresentation(final int id) {
        return restClient.get()
                .uri(apiBaseUrl + "/representations/" + id)
                .retrieve()
                .body(Representation.class);
    }
}
