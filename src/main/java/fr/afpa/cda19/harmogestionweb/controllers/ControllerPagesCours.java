package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Cours;
import fr.afpa.cda19.harmogestionweb.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


    /**
     * Constructeur du controller des pages des cours.
     *
     * @param coursService CoursService : service des cours.
     */
    @Autowired
    public ControllerPagesCours(final CoursService coursService) {

        this.coursService = coursService;
    }


    /**
     * Méthode d'accès à la page de planification de cours.
     *
     * @param model Modèle de la page.
     * @return URI de la page.
     */
    @GetMapping("/planifierCours")
    public String planifierCours(Model model) {

        return "planifierCours";
    }

    /**
     * Méthode d'accès à la page des prochains cours.
     *
     * @param model Modèle de la page.
     * @return URI de la page.
     */
    @GetMapping("/prochainsCours")
    public String prochainsCours(Model model) {

        ArrayList<Cours> listeCours = (ArrayList<Cours>) coursService.getAllCours();
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
