package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Instrument;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe de controller liée aux instruments.
 *
 * @author Cédric DIDIER
 * @version 1.0.0
 * @since 10/04/2026
 */
@Controller
public class ControllerPagesInstruments {

    /**
     * Méthode d'accès à la page d'ajout d'instrument.
     *
     * @param model Modèle de la page.
     * @return URI de la page.
     */
    @GetMapping("/ajouterInstrument")
    public String ajouterInstrument(Model model) {
        model.addAttribute("action", "/ajouterInstrument");
        model.addAttribute("nomAction", "Enregistrer");
        model.addAttribute("titreFormulaire", "Ajouter un instrument");
        model.addAttribute("titrePage", "HarmoGestion : Ajout d'un instrument");
        return "formulaireInstrument";
    }

    @PostMapping("/ajouterInstrument")
    public ModelAndView creerInstrument(
            @ModelAttribute
            @Valid
            final Instrument instrument, final BindingResult result) {
        return null;
    }
}
