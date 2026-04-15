package fr.afpa.cda19.harmogestionweb.services;

import fr.afpa.cda19.harmogestionweb.models.Membre;
import fr.afpa.cda19.harmogestionweb.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MembreService {
    //==== Variables ====
    /**
     * Repository des membres.
     */
    private final MembreRepository repository;

    //==== Constructeurs ====
    /**
     * Constructeurs.
     * @param repository Repository des membres.
     */
    public MembreService(
            @Autowired
            final MembreRepository repository) {
        this.repository = repository;
    }

    //==== Méthodes ====
    /**
     * Récupère tous les membres de la BDD via l'API.
     * @return Membres trouvés.
     */
    public Iterable<Membre> getMembres() {
        return repository.getMembres();
    }

    /**
     * Récupère un membre via son ID via l'API.
     * @param id ID voulu.
     * @return Membre éventuellement trouvé.
     */
    public Membre getMembre(final Integer id) {
        return repository.getMembre(id);
    }

    /**
     * Supprime un membre via son ID via l'API.
     * @param id ID voulu.
     */
    public void deleteMembre(final Integer id) {
        repository.deleteMembre(id);
    }

    /**
     * Met à jour ou crée un membre via son ID.
     * @param membre Membre à mettre à jour ou créer.
     * @return Membre mis à jour ou créé.
     */
    public Membre saveMembre(Membre membre) {
        Membre saved;
        membre.setNomMembre(
                StringUtils.capitalize(
                        membre.getNomMembre()
                )
        );
        membre.setPrenomMembre(
                StringUtils.capitalize(
                        membre.getPrenomMembre()
                )
        );
        membre.setDateInscriptionMembre(
                membre.getDateInscriptionMembre()
        );
        if (membre.getIdMembre() == null) {
            saved = repository.createMembre(membre);
        } else {
            saved = repository.updateMembre(membre);
        }
        return saved;
    }
}
