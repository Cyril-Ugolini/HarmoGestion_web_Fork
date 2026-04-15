package fr.afpa.cda19.harmogestionweb.repositories;

import fr.afpa.cda19.harmogestionweb.models.Membre;
import fr.afpa.cda19.harmogestionweb.utilities.CustomProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Repository côté membre.
 */
@Slf4j
@EnableConfigurationProperties(CustomProperties.class)
@Component
public class MembreRepository {
    //==== Variables ====
    /**
     * URL de base de l'API.
     */
    private final String baseApiUrl;
    /**
     * URI des endpoints create, read by id, update, et delete des membres.
     */
    private final String crudUri;

    //==== Constructeurs ====
    /**
     * Constructeur.
     * @param customProperties CustomProperties contenant le lien vers l'API.
     */
    public MembreRepository(
            @Autowired
            final CustomProperties customProperties) {
        baseApiUrl = customProperties.getApiUrl();
        crudUri = "/membre";
    }

    //==== Méthodes ====
    /**
     * Récupère tous les membres via l'API.
     * @return Membres trouvés.
     */
    public Iterable<Membre> getMembres() {
        String url = baseApiUrl + "/membres";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Membre>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    /**
     * Récupère un membre via son ID via l'API.
     * @param id ID du membre voulu.
     * @return Membre éventuellement trouvé.
     */
    public Membre getMembre(final Integer id) {
        String url = baseApiUrl + crudUri + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Membre> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Membre.class
        );
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            assert response.getBody() != null;
            String identiteMembre = response.getBody().getNomMembre() + " " + response.getBody().getPrenomMembre();
            log.info(identiteMembre);
            return null;
        }
        return response.getBody();
    }

    /**
     * Crée un membre dans la BDD via l'API.
     * @param membre Membre à sauvegarder.
     * @return Membre créé.
     */
    public Membre createMembre(final Membre membre) {
        String url = baseApiUrl + crudUri;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Membre> request = new HttpEntity<>(membre);
        ResponseEntity<Membre> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                Membre.class
        );
        String identiteMembre = response.getBody().getNomMembre() + " " + response.getBody().getPrenomMembre();
        switch (response.getStatusCode()) {
            case HttpStatus.CREATED:
                return response.getBody();
            case HttpStatus.BAD_REQUEST:
                assert response.getBody() != null;
                log.warn(identiteMembre);
                break;
            default:
                assert response.getBody() != null;
                log.error(identiteMembre);
        }
        return null;
    }

    /**
     * Mise à jour d'un membre via l'API.
     * @param membre Membre à mettre à jour.
     * @return Membre mis à jour.
     */
    public Membre updateMembre(final Membre membre) {
        String url = baseApiUrl + crudUri + "/" + membre.getIdMembre();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Membre> request = new HttpEntity<>(membre);
        ResponseEntity<Membre> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                Membre.class
        );
        String identiteMembre = response.getBody().getNomMembre() + " " + response.getBody().getPrenomMembre();
        switch (response.getStatusCode()) {
            case HttpStatus.OK:
                return response.getBody();
            case HttpStatus.BAD_REQUEST:
                assert response.getBody() != null;
                log.warn(identiteMembre);
                break;
            default:
                assert response.getBody() != null;
                log.error(identiteMembre);
        }
        return null;
    }

    /**
     * Supprime un membre via l'API.
     * @param id ID du membre à supprimer.
     */
    public void deleteMembre(final Integer id) {
        String url = baseApiUrl + crudUri + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
