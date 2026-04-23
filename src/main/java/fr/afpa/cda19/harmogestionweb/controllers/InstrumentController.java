package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Instrument;
import fr.afpa.cda19.harmogestionweb.services.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Contrôleur web gérant les vues des instruments.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@Controller
public class InstrumentController {

    /**
     * Instance du service des instruments.
     */
    private final InstrumentService instrumentService;

    /**
     * Constructeur du controller des instruments.
     *
     * @param instrumentService instance du service des instruments
     */
    @Autowired
    public InstrumentController(final InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    /**
     * Affiche le formulaire d'ajout d'instrument.
     *
     * @param model le modèle Thymeleaf
     * @return le nom de la vue Thymeleaf
     */
    @GetMapping("/instruments/ajouter")
    public String showForm(final Model model) {
        model.addAttribute("instrument", new Instrument());
        return "instrument-form";
    }

    /**
     * Traite le formulaire d'ajout d'instrument.
     *
     * @param instrument l'instrument à créer
     * @return redirection vers la liste des instruments
     */
    @PostMapping("/instruments/ajouter")
    public String saveInstrument(
            @ModelAttribute final Instrument instrument) {
        instrumentService.saveInstrument(instrument);
        return "redirect:/";
    }
}
