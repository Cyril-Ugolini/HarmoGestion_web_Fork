package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Cours;
import fr.afpa.cda19.harmogestionweb.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Contrôleur web gérant les vues des cours.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Controller
public class CoursController {

    /**
     * Instance du service des cours.
     */
    private final CoursService coursService;

    /**
     * Constructeur du controller des cours.
     *
     * @param coursService instance du service des cours
     */
    @Autowired
    public CoursController(final CoursService coursService) {
        this.coursService = coursService;
    }

    /**
     * Affiche la liste des cours.
     *
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/cours")
    public String getAllCours(final Model model) {
        List<Cours> cours = coursService.getAllCours();
        model.addAttribute("cours", cours);
        return "cours";
    }

    /**
     * Affiche le détail d'un cours.
     *
     * @param id    l'identifiant du cours
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/cours/{id}")
    public String getCours(
            @PathVariable final int id,
            final Model model) {
        Cours cours = coursService.getCours(id);
        model.addAttribute("cours", cours);
        return "cours-detail";
    }
}
