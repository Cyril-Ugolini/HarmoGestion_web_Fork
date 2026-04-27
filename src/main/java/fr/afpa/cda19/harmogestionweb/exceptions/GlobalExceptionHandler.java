package fr.afpa.cda19.harmogestionweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Gestionnaire global des exceptions pour l'application web.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 22/04/2026
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les erreurs 404 - ressource non trouvée.
     *
     * @param ex l'exception levée
     * @return la vue d'erreur avec le message approprié
     */
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ModelAndView handleNotFoundException(final HttpClientErrorException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", HttpStatus.NOT_FOUND.value());
        mav.addObject("message", "La ressource demandée n'existe pas.");
        return mav;
    }

    /**
     * Gère les erreurs 500 - erreur serveur API.
     *
     * @param ex l'exception levée
     * @return la vue d'erreur avec le message approprié
     */
    @ExceptionHandler(HttpServerErrorException.class)
    public ModelAndView handleServerErrorException(final HttpServerErrorException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        mav.addObject("message", "Une erreur est survenue côté serveur.");
        return mav;
    }

    /**
     * Gère les erreurs de connexion à l'API.
     *
     * @param ex l'exception levée
     * @return la vue d'erreur avec le message approprié
     */
    @ExceptionHandler(ResourceAccessException.class)
    public ModelAndView handleResourceAccessException(final ResourceAccessException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", 503);
        mav.addObject("message", "L'API est inaccessible. Veuillez réessayer plus tard.");
        return mav;
    }

    /**
     * Gère toutes les autres exceptions non prévues.
     *
     * @param ex l'exception levée
     * @return la vue d'erreur avec le message approprié
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(final Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        mav.addObject("message", "Une erreur inattendue s'est produite.");
        return mav;
    }
}
