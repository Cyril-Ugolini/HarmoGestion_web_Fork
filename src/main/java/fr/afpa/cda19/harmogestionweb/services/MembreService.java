package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Membre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Service gérant les appels à l'API pour les membres.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Service
public class MembreService {

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
     * Constructeur du service des membres.
     */
    public MembreService() {
        this.restClient = RestClient.create();
    }

    /**
     * Récupère tous les membres depuis l'API.
     *
     * @return la liste de tous les membres
     */
    public List<Membre> getAllMembres() {
        return restClient.get()
                .uri(apiBaseUrl + "/membres")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Membre>>() {});
    }

    /**
     * Récupère un membre par son identifiant depuis l'API.
     *
     * @param id l'identifiant du membre
     * @return le membre correspondant
     */
    public Membre getMembre(final int id) {
        return restClient.get()
                .uri(apiBaseUrl + "/membre/" + id)
                .retrieve()
                .body(Membre.class);
    }

    /**
     * Crée un nouveau membre via l'API.
     *
     * @param membre le membre à créer
     * @return le membre créé
     */
    public Membre saveMembre(final Membre membre) {
        return restClient.post()
                .uri(apiBaseUrl + "/membre")
                .body(membre)
                .retrieve()
                .body(Membre.class);
    }
}
