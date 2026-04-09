package fr.afpa.cda19.harmogestionweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller gérant l'affichage de la page de représentation.
 *
 * Cet endpoint envoie à la vue Thymeleaf :
 * - une liste d'instruments
 * - une liste de membres
 *
 * La page HTML representation.html utilise ces données via Thymeleaf.
 */
@Controller
public class RepresentationController {

    /**
     * Endpoint principal affichant la page de représentation.
     *
     * URL : /representation
     * Méthode : GET
     */
    @GetMapping("/representation")
    public String representation(Model model) {

        // Liste des instruments affichés dans la page
        List<String> instruments = List.of(
                "Flûte",
                "Clarinette",
                "Trompette",
                "Triangle"
        );

        // Liste des membres affichés dans la page
        List<String> membres = List.of(
                "Rodolphe BRUCKER",
                "Cyril UGOLINI",
                "Cédric DIDIER",
                "Thomas SEIWERT"
        );

        // Envoi des données à la vue Thymeleaf
        model.addAttribute("instruments", instruments);
        model.addAttribute("membres", membres);

        // Nom du fichier HTML dans /templates
        return "representation.html";
    }
}
