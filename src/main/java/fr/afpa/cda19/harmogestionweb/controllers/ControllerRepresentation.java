package fr.afpa.cda19.harmogestionweb.controllers;

import fr.afpa.cda19.harmogestionweb.models.Instrument;
import fr.afpa.cda19.harmogestionweb.models.Membre;
import fr.afpa.cda19.harmogestionweb.models.Representation;
import fr.afpa.cda19.harmogestionweb.models.RepresentationForm;
import fr.afpa.cda19.harmogestionweb.services.InstrumentService;
import fr.afpa.cda19.harmogestionweb.services.MembreService;
import fr.afpa.cda19.harmogestionweb.services.RepresentationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur Web MVC gérant l'affichage et la création
 * des représentations musicales dans l'application HarmoGestion.
 *
 * <p>Ce contrôleur assure :</p>
 * <ul>
 *     <li>l'affichage de la liste des représentations</li>
 *     <li>l'affichage du formulaire de planification</li>
 *     <li>le traitement de la création d'une représentation</li>
 * </ul>
 *
 * <p>Il communique avec les services métier pour récupérer les membres,
 * les instruments et enregistrer une nouvelle représentation.</p>
 */
@Controller
public class ControllerRepresentation {

    /** Service permettant de récupérer et créer des représentations. */
    private final RepresentationService representationService;

    /** Service permettant de récupérer les membres disponibles. */
    private final MembreService membreService;

    /** Service permettant de récupérer les instruments disponibles. */
    private final InstrumentService instrumentService;

    /**
     * Injection des services nécessaires au contrôleur.
     *
     * @param representationService service de gestion des représentations
     * @param membreService service de gestion des membres
     * @param instrumentService service de gestion des instruments
     */
    public ControllerRepresentation(RepresentationService representationService,
                                    MembreService membreService,
                                    InstrumentService instrumentService) {
        this.representationService = representationService;
        this.membreService = membreService;
        this.instrumentService = instrumentService;
    }

    /**
     * GET /prochainesRepresentations
     *
     * <p>Affiche la liste des représentations existantes.</p>
     *
     * @param model modèle Thymeleaf contenant la liste des représentations
     * @return la vue {@code prochainesRepresentations.html}
     */
    @GetMapping("/prochainesRepresentations")
    public String prochainesRepresentations(Model model) {

        model.addAttribute("representations", representationService.getAll());

        return "prochainesRepresentations";
    }

    /**
     * GET /planifierRepresentation
     *
     * <p>Affiche le formulaire permettant de créer une nouvelle représentation.</p>
     *
     * <p>Le modèle contient :</p>
     * <ul>
     *     <li>un objet {@link RepresentationForm} vide</li>
     *     <li>la liste des membres disponibles</li>
     *     <li>la liste des instruments disponibles</li>
     * </ul>
     *
     * @param model modèle Thymeleaf contenant les données du formulaire
     * @return la vue {@code planifierRepresentation.html}
     */
    @GetMapping("/planifierRepresentation")
    public String planifierRepresentation(Model model) {

        model.addAttribute("representationForm", new RepresentationForm());
        model.addAttribute("membres", membreService.getAll());
        model.addAttribute("instruments", instrumentService.getAll());

        return "planifierRepresentation";
    }

    /**
     * POST /planifierRepresentation
     *
     * <p>Traite la soumission du formulaire de création d'une représentation.</p>
     *
     * <p>Étapes :</p>
     * <ol>
     *     <li>Conversion des IDs de membres en objets {@link Membre}</li>
     *     <li>Conversion des IDs d'instruments en objets {@link Instrument}</li>
     *     <li>Construction d'un objet {@link Representation}</li>
     *     <li>Envoi de la représentation au service pour création</li>
     * </ol>
     *
     * @param form données du formulaire envoyées par l'utilisateur
     * @return redirection vers la liste des représentations
     */
    @PostMapping("/planifierRepresentation")
    public String creerRepresentation(@ModelAttribute RepresentationForm form) {

        // Convertir les IDs → objets Membre
        List<Membre> participants = new ArrayList<>();
        if (form.getParticipantsIds() != null) {
            for (Integer id : form.getParticipantsIds()) {
                participants.add(membreService.getById(id));
            }
        }

        // Convertir les IDs → objets Instrument
        List<Instrument> instruments = new ArrayList<>();
        if (form.getInstrumentsIds() != null) {
            for (Integer id : form.getInstrumentsIds()) {
                instruments.add(instrumentService.getById(id));
            }
        }

        // Construire l'objet Representation
        Representation r = new Representation();
        r.setNomRepresentation(form.getNomRepresentation());
        r.setDateRepresentation(form.getDateRepresentation());
        r.setLieuRepresentation(form.getLieuRepresentation());
        r.setParticipants(participants);
        r.setInstruments(instruments);

        // Appel API via le service
        representationService.create(r);

        return "redirect:/prochainesRepresentations";
    }
}
