package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Representation;
import fr.afpa.cda19.harmogestionweb.services.RepresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Contrôleur web gérant les vues des représentations.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Controller
public class RepresentationController {

    /**
     * Instance du service des représentations.
     */
    private final RepresentationService representationService;

    /**
     * Constructeur du controller des représentations.
     *
     * @param representationService instance du service des représentations
     */
    @Autowired
    public RepresentationController(final RepresentationService representationService) {
        this.representationService = representationService;
    }

    /**
     * Affiche la liste des représentations.
     *
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/representations")
    public String getAllRepresentations(final Model model) {
        List<Representation> representations = representationService.getAllRepresentations();
        model.addAttribute("representations", representations);
        return "representations";
    }

    /**
     * Affiche le détail d'une représentation.
     *
     * @param id    l'identifiant de la représentation
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/representations/{id}")
    public String getRepresentation(
            @PathVariable final int id,
            final Model model) {
        Representation representation = representationService.getRepresentation(id);
        model.addAttribute("representation", representation);
        return "representation-detail";
    }
}
