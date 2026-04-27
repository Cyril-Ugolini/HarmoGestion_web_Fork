package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Membre;
import fr.afpa.cda19.harmogestionweb.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Contrôleur web gérant les vues des membres.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Controller
public class MembreController {

    /**
     * Instance du service des membres.
     */
    private final MembreService membreService;

    /**
     * Constructeur du controller des membres.
     *
     * @param membreService instance du service des membres
     */
    @Autowired
    public MembreController(final MembreService membreService) {
        this.membreService = membreService;
    }

    /**
     * Affiche la liste des membres.
     *
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/membres")
    public String getAllMembres(final Model model) {
        List<Membre> membres = membreService.getAllMembres();
        model.addAttribute("membres", membres);
        return "membres";
    }

    /**
     * Affiche la fiche d'un membre.
     *
     * @param id    l'identifiant du membre
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/membres/{id}")
    public String getMembre(
            @PathVariable final int id,
            final Model model) {
        Membre membre = membreService.getMembre(id);
        model.addAttribute("membre", membre);
        return "membre-detail";
    }

    /**
     * Affiche le formulaire d'inscription d'un membre.
     *
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/membres/inscription")
    public String showForm(final Model model) {
        model.addAttribute("membre", new Membre());
        return "membre-form";
    }

    /**
     * Traite le formulaire d'inscription d'un membre.
     *
     * @param membre le membre à créer
     * @return redirection vers la liste des membres
     */
    @PostMapping("/membres/inscription")
    public String saveMembre(
            @ModelAttribute final Membre membre) {
        membreService.saveMembre(membre);
        return "redirect:/membres";
    }
}
