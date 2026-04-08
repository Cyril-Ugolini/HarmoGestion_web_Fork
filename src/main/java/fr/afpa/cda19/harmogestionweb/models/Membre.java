package fr.afpa.cda19.harmogestionweb.models;

import java.time.LocalDate;

public class Membre {
    //==== Variables ====
    private int idMembre;
    private String nomMembre;
    private String prenomMembre;
    private LocalDate dateInscriptionMembre;

    //==== Getter et Setter ====
    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(final int idMembre) {
        this.idMembre = idMembre;
    }

    public String getNomMembre() {
        return nomMembre;
    }

    public void setNomMembre(final String nomMembre) {
        this.nomMembre = nomMembre;
    }

    public String getPrenomMembre() {
        return prenomMembre;
    }

    public void setPrenomMembre(final String prenomMembre) {
        this.prenomMembre = prenomMembre;
    }

    public LocalDate getDateInscriptionMembre() {
        return dateInscriptionMembre;
    }

    public void setDateInscriptionMembre(
            final LocalDate dateInscriptionMembre) {
        this.dateInscriptionMembre = dateInscriptionMembre;
    }


    //==== Constructeurs ====
    public Membre(final int idMembre, final String nomMembre,
                  final String prenomMembre,
                  final LocalDate dateInscriptionMembre) {
        this.idMembre = idMembre;
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
        this.dateInscriptionMembre = dateInscriptionMembre;
    }
}
