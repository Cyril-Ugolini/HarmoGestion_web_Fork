package fr.afpa.cda19.harmogestionweb.repositories;

import fr.afpa.cda19.harmogestionweb.models.Representation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RepresentationRepository {

    private static final String BASE_API_URL = "http://localhost:9003/representations";

    private final RestTemplate restTemplate;

    public RepresentationRepository() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Récupère toutes les représentations.
     */
    public Iterable<Representation> getAll() {

        ResponseEntity<Iterable<Representation>> response = restTemplate.exchange(
                BASE_API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        log.debug("GET ALL → Status = {}", response.getStatusCode());
        log.debug("GET ALL → Body   = {}", response.getBody());

        return response.getBody();
    }

    /**
     * Récupère une représentation par ID.
     */
    public Representation getRepresentation(int id) {

        String url = BASE_API_URL + "/" + id;

        ResponseEntity<Representation> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Representation.class
        );

        log.debug("GET ONE → Status = {}", response.getStatusCode());
        log.debug("GET ONE → Body   = {}", response.getBody());

        return response.getBody();
    }

    /**
     * Crée une nouvelle représentation (POST).
     */
    public Representation create(Representation representation) {

        HttpEntity<Representation> request = new HttpEntity<>(representation);

        ResponseEntity<Representation> response = restTemplate.exchange(
                BASE_API_URL,
                HttpMethod.POST,
                request,
                Representation.class
        );

        log.debug("POST → Status = {}", response.getStatusCode());
        log.debug("POST → Body   = {}", response.getBody());

        return response.getBody();
    }

    /**
     * Met à jour une représentation existante (PUT).
     */
    public Representation update(Representation representation) {

        String url = BASE_API_URL + "/" + representation.getIdRepresentation();

        HttpEntity<Representation> request = new HttpEntity<>(representation);

        ResponseEntity<Representation> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                Representation.class
        );

        log.debug("PUT → Status = {}", response.getStatusCode());
        log.debug("PUT → Body   = {}", response.getBody());

        return response.getBody();
    }

    /**
     * Supprime une représentation (DELETE).
     */
    public void delete(int id) {

        String url = BASE_API_URL + "/" + id;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("DELETE → Status = {}", response.getStatusCode());
    }
}