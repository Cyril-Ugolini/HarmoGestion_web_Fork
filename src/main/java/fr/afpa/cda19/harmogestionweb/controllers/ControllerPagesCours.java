package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Cours;
import fr.afpa.cda19.harmogestionweb.models.Instrument;
import fr.afpa.cda19.harmogestionweb.models.Membre;
import fr.afpa.cda19.harmogestionweb.services.CoursService;
import fr.afpa.cda19.harmogestionweb.services.InstrumentService;
import fr.afpa.cda19.harmogestionweb.services.MembreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe de controller liée aux membres.
 */
@Controller
public class ControllerPagesCours {

    /**
     * Instance du service des cours.
     */
    private final CoursService coursService;

    private final InstrumentService instrumentService;

    private final MembreService membreService;


    /**
     * Constructeur du controller des pages des cours.
     *
     * @param coursService CoursService : service des cours.
     */
    @Autowired
    public ControllerPagesCours(final CoursService coursService,
                                final InstrumentService instrumentService,
                                final MembreService membreService) {

        this.coursService = coursService;
        this.instrumentService = instrumentService;
        this.membreService = membreService;
    }


    /**
     * Méthode d'accès à la page de création de cours.
     *
     * @param model Modèle de la page.
     * @return URI de la page.
     */
    @GetMapping("/creerCours")
    public String ajouterCours(Model model) {

        Iterable<Instrument> instruments = instrumentService.getInstruments();
        Iterable<Membre> membres = membreService.getMembres();

        model.addAttribute("action", "/creerCours");
        model.addAttribute("nomSubmit", "Créer");
        model.addAttribute("titreFormulaire", "Créer un cours");
        model.addAttribute("titrePage", "Créer un cours");
        model.addAttribute("cours", new Cours());
        model.addAttribute("instruments", instruments);
        model.addAttribute("membres", membres);

        return "gererCours";
    }

    @PostMapping("/creerCours")
    public String creerCours(
            @ModelAttribute
            @Valid
            final Cours cours, final BindingResult result) {

        return null;
    }


    /**
     * Méthode d'accès à la page des prochains cours.
     *
     * @param model Modèle de la page.
     * @return URI de la page.
     */
    @GetMapping("/prochainsCours")
    public String prochainsCours(Model model) {

        ArrayList<Cours> listeCours =
                (ArrayList<Cours>) coursService.getAllCours();
        listeCours.sort(Cours.COMPARATOR_DATE);
        ArrayList<Cours> prochainsCours = new ArrayList<>();
        int i = 0;

        while (prochainsCours.size() <= 3 && i < listeCours.size()) {
            Cours cours = listeCours.get(i);
            if (cours.getDateCours().isAfter(LocalDateTime.now())) {
                prochainsCours.add(cours);
            }
            i++;
        }
        model.addAttribute("prochainsCours", prochainsCours);
        model.addAttribute("titrePage", "Prochains Cours");

        return "prochainsCours";
    }
}
