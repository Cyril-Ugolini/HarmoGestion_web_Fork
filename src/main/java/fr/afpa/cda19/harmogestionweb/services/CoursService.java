package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Cours;
import fr.afpa.cda19.harmogestionweb.repositories.CoursRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service pour les cours.
 *
 * @author Seiwert Thomas
 * @version 0.0.1
 * @since 10/04/2026
 */
@Service
@Data
public class CoursService {

    /**
     * Instance de la repository des cours.
     */
    private CoursRepository coursRepository;


    /**
     * Constructeur du service des cours.
     *
     * @param coursRepository CoursRepository : repository des cours.
     */
    @Autowired
    public CoursService(final CoursRepository coursRepository) {

        this.coursRepository = coursRepository;
    }


    public Iterable<Cours> getAllCours() {

        return coursRepository.getAllCours();
    }

    public Cours getCours(final int id) {

        return coursRepository.getCours(id);
    }

    public Cours saveCours(final Cours cours) {

        if (cours.getIdCours() == null) {
            return coursRepository.createCours(cours);
        } else {
            return coursRepository.updateCours(cours);
        }
    }

    public void deleteCours(final int id) {
        coursRepository.deleteCours(id);
    }
}
