package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Representation;
import fr.afpa.cda19.harmogestionweb.repositories.RepresentationRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class RepresentationService {

    private final RepresentationRepository representationRepository;

    /**
     * Récupère une représentation par son ID.
     */
    public Representation getRepresentation(int id) {
        return representationRepository.getRepresentation(id);
    }

    /**
     * Récupère toutes les représentations.
     */
    public Iterable<Representation> getAll() {
        return representationRepository.getAll();
    }

    /**
     * Crée une nouvelle représentation.
     */
    public Representation create(Representation representation) {
        return representationRepository.create(representation);
    }

    /**
     * Met à jour une représentation existante.
     */
    public Representation update(Representation representation) {
        return representationRepository.update(representation);
    }

    /**
     * Supprime une représentation.
     */
    public void deleteRepresentation(int id) {
        representationRepository.delete(id);
    }
}
