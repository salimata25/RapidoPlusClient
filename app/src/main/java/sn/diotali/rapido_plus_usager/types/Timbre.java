package sn.diotali.rapido_plus_usager.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timbre implements Serializable {
    private String type;

    private String libelle;

    private int prixU;

    private int quantite;


    public Timbre() { }

    public Timbre(String type, String libelle, int prixU, int quantite) {
        this.type = type;
        this.libelle = libelle;
        this.prixU = prixU;
        this.quantite = quantite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getPrixU() {
        return prixU;
    }

    public void setPrixU(int prixU) {
        this.prixU = prixU;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Timbre{" +
                "type='" + type + '\'' +
                ", libelle='" + libelle + '\'' +
                ", prixU=" + prixU +
                ", quantite=" + quantite +
                '}';
    }
}
