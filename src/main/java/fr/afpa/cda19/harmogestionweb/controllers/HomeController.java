package fr.afpa.cda19.harmogestionweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur web pour la page d'accueil.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Controller
public class HomeController {

    /**
     * Affiche la page d'accueil.
     *
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
