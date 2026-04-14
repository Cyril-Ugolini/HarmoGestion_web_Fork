package fr.afpa.cda19.harmogestionweb.controllers;

public interface IController {
    /**
     * Paramètre 'action' du formulaire.
     */
    String ACTION_PARAM = "action";

    /**
     * Nom du bouton submit du formulaire.
     */
    String NOM_SUBMIT_PARAM = "nomSubmit";

    /**
     * Titre de la page.
     */
    String TITRE_PAGE_PARAM = "titrePage";

    /**
     * Titre du formulaire.
     */
    String TITRE_FORM_PARAM = "titreFormulaire";
}
