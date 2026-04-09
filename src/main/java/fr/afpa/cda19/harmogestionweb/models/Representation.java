package fr.afpa.cda19.harmogestionweb.models;

import java.time.LocalDate;

/**
 * Modèle représentant une représentation musicale.
 *
 * Contient :
 * - un identifiant
 * - un titre (ex : "5ème Symphonie")
 * - un lieu (ex : "Salle des fêtes de Liverdun")
 * - une date
 */
public class Representation {

    //==== Variables ====
    private int idRepresentation;
    private String titreRepresentation;
    private String lieuRepresentation;
    private LocalDate dateRepresentation;

    //==== Getters et Setters ====
    public int getIdRepresentation() {
        return idRepresentation;
    }

    public void setIdRepresentation(final int idRepresentation) {
        this.idRepresentation = idRepresentation;
    }

    public String getTitreRepresentation() {
        return titreRepresentation;
    }

    public void setTitreRepresentation(final String titreRepresentation) {
        this.titreRepresentation = titreRepresentation;
    }

    public String getLieuRepresentation() {
        return lieuRepresentation;
    }

    public void setLieuRepresentation(final String lieuRepresentation) {
        this.lieuRepresentation = lieuRepresentation;
    }

    public LocalDate getDateRepresentation() {
        return dateRepresentation;
    }

    public void setDateRepresentation(final LocalDate dateRepresentation) {
        this.dateRepresentation = dateRepresentation;
    }

    //==== Constructeur ====
    public Representation(final int idRepresentation,
                          final String titreRepresentation,
                          final String lieuRepresentation,
                          final LocalDate dateRepresentation) {

        this.idRepresentation = idRepresentation;
        this.titreRepresentation = titreRepresentation;
        this.lieuRepresentation = lieuRepresentation;
        this.dateRepresentation = dateRepresentation;
    }
}
