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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

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
     * Instance du service des instruments.
     */
    private final InstrumentService instrumentService;

    /**
     * Instance du service des membres.
     */
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
     * Méthode d'accès à la page de création de cours vierge.
     *
     * @param model Modèle de la page.
     * @return URI de la page.
     */
    @GetMapping("/creerCours")
    public String creerCours(Model model) {

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

    /**
     * Méthode d'accès à la page de création de cours pour un retour de formulaire.
     *
     * @param cours  Cours : cours entré dans le formulaire.
     * @param result BindingResult : erreurs du formulaire.
     * @param model  Modèle de la page.
     * @return URI de la page.
     */
    @PostMapping("/creerCours")
    public String creerCours(
            @ModelAttribute
            @Valid
            final Cours cours, final BindingResult result, Model model) {

        Iterable<Instrument> instruments = instrumentService.getInstruments();
        Iterable<Membre> membres = membreService.getMembres();

        cours.getParticipants().removeIf(p -> (p.getIdMembre() == null));

        if (result.hasErrors() || cours.getEnseignant().getIdMembre() == null
            || cours.getInstrument().getIdInstrument() == null
            || cours.getParticipants().isEmpty()
            || cours.getParticipants().size() > 15) {
            model.addAttribute("cours", cours);
            if (cours.getParticipants().isEmpty() ||
                cours.getParticipants().size() > 15) {
                model.addAttribute("errParticipants",
                                   "Le nombre de participants doit être entre 1 et 15");
            }
        } else {
            model.addAttribute("cours", new Cours());
            model.addAttribute("statut", "Création réussie");
        }
        model.addAttribute("action", "/creerCours");
        model.addAttribute("nomSubmit", "Créer");
        model.addAttribute("titreFormulaire", "Créer un cours");
        model.addAttribute("titrePage", "Créer un cours");
        model.addAttribute("instruments", instruments);
        model.addAttribute("membres", membres);

        return "gererCours";
    }

    /**
     * Méthode d'accès à la sélection du cours à modifier.
     *
     * @param statut String : Modification ou suppression réussie
     * @param model  Modèle de la page
     * @return URI de la page
     */
    @GetMapping("/modifierCours")
    public String modifierCours(
            @RequestParam(name = "statut", required = false)
            final Optional<String> statut,
            Model model) {

        ArrayList<Cours> listeCours =
                (ArrayList<Cours>) coursService.getAllCours();
        listeCours.sort(Cours.COMPARATOR_DATE);

        if (statut.isPresent()) {
            if (statut.get().equals("modified")) {
                model.addAttribute("statut", "Modification réussie");
            } else {
                model.addAttribute("statut", "Suppression réussie");
            }
        }
        model.addAttribute("listeCours", listeCours);
        model.addAttribute("action", "/modifierCours");
        model.addAttribute("nomSubmit", "Modifier");
        model.addAttribute("titreFormulaire", "Modifier un cours");
        model.addAttribute("titrePage", "Modifier un cours");

        return "selectCours";

    }

    /**
     * Méthode d'accès à la page de modification d'un cours.
     *
     * @param id    int : id du cours
     * @param model Modèle de la page
     * @return URI de la page
     */
    @PostMapping("/modifierCours")
    public String modifierCours(
            @RequestParam("cours")
            final int id,
            Model model) {

        Iterable<Instrument> instruments = instrumentService.getInstruments();
        Iterable<Membre> membres = membreService.getMembres();

        Cours cours = coursService.getCours(id);
        model.addAttribute("cours", cours);
        model.addAttribute("action", "/modifierCours/" + id);
        model.addAttribute("nomSubmit", "Modifier");
        model.addAttribute("titreFormulaire", "Modifier un cours");
        model.addAttribute("titrePage", "Modifier un cours");
        model.addAttribute("instruments", instruments);
        model.addAttribute("membres", membres);

        return "gererCours";
    }

    /**
     * Méthode d'accès à la page de modification de cours pour retour de formulaire.
     *
     * @param id int : id du cours
     * @param cours Cours : le cours
     * @param result : erreurs
     * @param model Modèle de la page
     * @return page de formulaire si erreur, redirect à la selection de cours sinon
     */
    @PostMapping("/modifierCours/{id}")
    public ModelAndView modifierCours(
            @PathVariable
            final int id,
            @ModelAttribute
            @Valid
            final Cours cours, final BindingResult result, ModelMap model) {

        Iterable<Instrument> instruments = instrumentService.getInstruments();
        Iterable<Membre> membres = membreService.getMembres();

        cours.getParticipants().removeIf(p -> (p.getIdMembre() == null));

        if (result.hasErrors() || cours.getEnseignant().getIdMembre() == null
            || cours.getInstrument().getIdInstrument() == null
            || cours.getParticipants().isEmpty()
            || cours.getParticipants().size() > 15) {
            model.addAttribute("cours", cours);
            model.addAttribute("action", "/modifierCours/" + id);
            model.addAttribute("instruments", instruments);
            model.addAttribute("membres", membres);
            model.addAttribute("nomSubmit", "Modifier");
            model.addAttribute("titreFormulaire", "Modifier un cours");
            model.addAttribute("titrePage", "Modifier un cours");
            if (cours.getParticipants().isEmpty() ||
                cours.getParticipants().size() > 15) {
                model.addAttribute("errParticipants",
                                   "Le nombre de participants doit être entre 1 et 15");
            }
            return new ModelAndView("gererCours");
        } else {
            model.addAttribute("statut", "modified");
            return new ModelAndView("redirect:/modifierCours", model);
        }
    }

    /**
     * Méthode d'accès à la sélection du cours à supprimer.
     *
     * @param statut String : Modification ou suppression réussie
     * @param model  Modèle de la page
     * @return URI de la page
     */
    @GetMapping("/supprimerCours")
    public String supprimerCours(
            @RequestParam(name = "statut", required = false)
            final Optional<String> statut,
            Model model) {

        ArrayList<Cours> listeCours =
                (ArrayList<Cours>) coursService.getAllCours();
        listeCours.sort(Cours.COMPARATOR_DATE);

        if (statut.isPresent()) {
            if (statut.get().equals("modified")) {
                model.addAttribute("statut", "Modification réussie");
            } else {
                model.addAttribute("statut", "Suppression réussie");
            }
        }
        model.addAttribute("listeCours", listeCours);
        model.addAttribute("action", "/supprimerCours");
        model.addAttribute("nomSubmit", "Supprimer");
        model.addAttribute("titreFormulaire", "Supprimer un cours");
        model.addAttribute("titrePage", "Supprimer un cours");

        return "selectCours";

    }

    /**
     * Méthode d'accès à la page de suppression d'un cours.
     *
     * @param id    int : id du cours
     * @param model Modèle de la page
     * @return URI de la page
     */
    @PostMapping("/supprimerCours")
    public String supprimerCours(
            @RequestParam("cours")
            final int id,
            Model model) {

        Iterable<Instrument> instruments = instrumentService.getInstruments();
        Iterable<Membre> membres = membreService.getMembres();

        Cours cours = coursService.getCours(id);
        model.addAttribute("cours", cours);
        model.addAttribute("action", "/supprimerCours/" + id);
        model.addAttribute("nomSubmit", "Supprimer");
        model.addAttribute("titreFormulaire", "Supprimer un cours");
        model.addAttribute("titrePage", "Supprimer un cours");
        model.addAttribute("instruments", instruments);
        model.addAttribute("membres", membres);

        return "gererCours";
    }

    /**
     * Méthode d'accès à la page de suppression de cours pour retour de formulaire.
     *
     * @param id int : id du cours
     * @param model Modèle de la page
     * @return redirect à la selection de cours
     */
    @PostMapping("/supprimerCours/{id}")
    public ModelAndView supprimerCours(
            @PathVariable
            final int id,
            ModelMap model) {

        model.addAttribute("statut", "deleted");

        return new ModelAndView("redirect:/supprimerCours", model);
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
