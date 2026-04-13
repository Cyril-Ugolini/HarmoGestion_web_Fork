package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Instrument;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
     * Paramètre 'action' du formulaire.
     */
    private static final String ACTION_PARAM = "action";

    /**
     * Nom du bouton submit du formulaire.
     */
    private static final String NOM_SUBMIT_PARAM = "nomSubmit";

    /**
     * Titre de la page.
     */
    private static final String TITRE_PAGE_PARAM = "titrePage";

    /**
     * Titre du formulaire.
     */
    private static final String TITRE_FORM_PARAM = "titreFormulaire";

    /**
     * Nom du modèle.
     */
    private static final String NOM_MODEL_PARAM = "instrument";

    /**
     * Nom de la vue.
     */
    private static final String NOM_FORM_VUE = "formulaireInstrument";

    /**
     * Méthode d'accès au formulaire d'ajout d'un instrument.
     *
     * @param model Modèle de la page.
     * @return le nom de la vue à afficher
     */
    @GetMapping("/ajouterInstrument")
    public String envoyerFormulaireAjoutInstrument(final Model model) {
        model.mergeAttributes(getParamsFormAjout());
        model.addAttribute(NOM_MODEL_PARAM, new Instrument());
        return NOM_FORM_VUE;
    }

    /**
     * Méthode de validation et d'enregistrement du nouvel instrument.
     *
     * @param instrument L'instrument à enregistrer
     * @param result     Le résultât de la validation de l'instrument
     * @return La redirection vers la pae d'accueil ou renvoie le formulaire
     * si une erreur a été détectée lors de la validation
     */
    @PostMapping("/ajouterInstrument")
    public ModelAndView creerInstrument(
            @ModelAttribute
            @Valid
            final Instrument instrument, final BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelView = new ModelAndView(NOM_FORM_VUE);
            modelView.addAllObjects(getParamsFormAjout());
            return modelView;
        }
        IO.println(instrument);
        //TODO: envoyer instrument à l'API et traiter le retour
        return new ModelAndView("redirect:/");
    }

    /**
     * Méthode d'accès au formulaire de modification d'un instrument.
     *
     * @param id    L'identifiant de l'instrument à modifier
     * @param model Modèle de la page.
     * @return le nom de la vue à afficher
     */
    @GetMapping("/modifierInstrument/{id}")
    public String envoyerFormulaireModificationInstrument(
            @PathVariable
            final Integer id, final Model model) {
        model.mergeAttributes(getParamsFormModification(id));
        //TODO: recup instrument depuis l'API
        model.addAttribute(NOM_MODEL_PARAM, new Instrument(id, "Ukulele"));
        return NOM_FORM_VUE;
    }

    /**
     * Méthode de validation et d'enregistrement de la modification d'un
     * instrument.
     *
     * @param id         L'identifiant de l'instrument
     * @param instrument L'instrument à modifier
     * @param result     Le résultât de la validation de l'instrument
     * @return La redirection vers la pae d'accueil ou renvoie le formulaire
     * si une erreur a été détectée lors de la validation
     */
    @PostMapping("/modifierInstrument/{id}")
    public ModelAndView modifierInstrument(
            @PathVariable
            final Integer id,
            @ModelAttribute
            @Valid
            final Instrument instrument, final BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelView = new ModelAndView(NOM_FORM_VUE);
            modelView.addAllObjects(getParamsFormModification(id));
            return modelView;
        }
        IO.println(instrument);
        //TODO: envoyer instrument à l'API et traiter le retour
        return new ModelAndView("redirect:/");
    }

    /**
     * Méthode d'accès au formulaire de modification d'un instrument.
     *
     * @param id    L'identifiant de l'instrument à modifier
     * @param model Modèle de la page.
     * @return le nom de la vue à afficher
     */
    @GetMapping("/supprimerInstrument/{id}")
    public String envoyerFormulaireSuppressionInstrument(
            @PathVariable
            final Integer id, final Model model) {
        model.mergeAttributes(getParamsFormSuppression(id));
        //TODO: recup instrument depuis l'API
        model.addAttribute(NOM_MODEL_PARAM, new Instrument(id, "Ukulele"));
        return NOM_FORM_VUE;
    }

    /**
     * Méthode de suppression d'un instrument.
     *
     * @param id         L'identifiant de l'instrument
     * @param instrument L'instrument à supprimer
     * @return La redirection vers la pae d'accueil
     */
    @PostMapping("/supprimerInstrument/{id}")
    public ModelAndView supprimerInstrument(
            @PathVariable
            final Integer id,
            @ModelAttribute
            final Instrument instrument) {
        IO.println(instrument);
        //TODO: envoyer instrument à l'API et traiter le retour
        return new ModelAndView("redirect:/");
    }

    /**
     * Récupère les paramètres du formulaire d'ajout d'un instrument.
     *
     * @return les paramètres du formulaire d'ajout
     */
    private Map<String, String> getParamsFormAjout() {
        HashMap<String, String> paramsFormAjout = new HashMap<>();
        paramsFormAjout.put(ACTION_PARAM, "/ajouterInstrument");
        paramsFormAjout.put(NOM_SUBMIT_PARAM, "Enregistrer");
        paramsFormAjout.put(TITRE_FORM_PARAM, "Ajouter un instrument");
        paramsFormAjout.put(TITRE_PAGE_PARAM,
                            "HarmoGestion : Ajout d'un instrument");
        return paramsFormAjout;
    }

    /**
     * Récupère les paramètres du formulaire de modification d'un instrument.
     *
     * @param id L'identifiant de l'instrument à modifier
     * @return les paramètres du formulaire de modification
     */
    private Map<String, String> getParamsFormModification(final Integer id) {
        HashMap<String, String> paramsFormModif = new HashMap<>();
        paramsFormModif.put(ACTION_PARAM, "/modifierInstrument/" + id);
        paramsFormModif.put(NOM_SUBMIT_PARAM, "Modifier");
        paramsFormModif.put(TITRE_FORM_PARAM, "Modifier un instrument");
        paramsFormModif.put(TITRE_PAGE_PARAM,
                            "HarmoGestion : Modification d'un instrument");
        return paramsFormModif;
    }

    /**
     * Récupère les paramètres du formulaire de suppression d'un instrument.
     *
     * @param id L'identifiant de l'instrument à supprimer
     * @return les paramètres du formulaire de suppression
     */
    private Map<String, String> getParamsFormSuppression(final Integer id) {
        HashMap<String, String> paramsFormSupp = new HashMap<>();
        paramsFormSupp.put(ACTION_PARAM, "/supprimerInstrument/" + id);
        paramsFormSupp.put(NOM_SUBMIT_PARAM, "Supprimer");
        paramsFormSupp.put(TITRE_FORM_PARAM, "Supprimer un instrument");
        paramsFormSupp.put(TITRE_PAGE_PARAM,
                            "HarmoGestion : Suppression d'un instrument");
        return paramsFormSupp;
    }
}
