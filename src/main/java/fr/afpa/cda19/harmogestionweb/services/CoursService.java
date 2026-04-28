package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Cours;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Service gérant les appels à l'API pour les cours.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Service
public class CoursService {

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
     * Constructeur du service des cours.
     */
    public CoursService() {
        this.restClient = RestClient.create();
    }

    /**
     * Récupère tous les cours depuis l'API.
     *
     * @return la liste de tous les cours
     */
    public List<Cours> getAllCours() {
        return restClient.get()
                .uri(apiBaseUrl + "/cours")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Cours>>() {});
    }

    /**
     * Récupère un cours par son identifiant depuis l'API.
     *
     * @param id l'identifiant du cours
     * @return le cours correspondant
     */
    public Cours getCours(final int id) {
        return restClient.get()
                .uri(apiBaseUrl + "/cours/" + id)
                .retrieve()
                .body(Cours.class);
    }
}
