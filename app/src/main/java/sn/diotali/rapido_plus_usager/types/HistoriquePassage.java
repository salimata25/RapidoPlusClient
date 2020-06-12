package sn.diotali.rapido_plus_usager.types;

public class HistoriquePassage {
    private String num_carte;
    private String sens;
    private String gare;
    private String voie;
    private int credit_initial;
    private int montant;
    private int credit_restant;
    private int solde;

    public HistoriquePassage(String num_carte, String sens, String gare, String voie, int credit_initial, int montant, int credit_restant, int solde) {
        this.num_carte = num_carte;
        this.sens = sens;
        this.gare = gare;
        this.voie = voie;
        this.credit_initial = credit_initial;
        this.montant = montant;
        this.credit_restant = credit_restant;
        this.solde = solde;
    }

    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getGare() {
        return gare;
    }

    public void setGare(String gare) {
        this.gare = gare;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public int getCredit_initial() {
        return credit_initial;
    }

    public void setCredit_initial(int credit_initial) {
        this.credit_initial = credit_initial;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getCredit_restant() {
        return credit_restant;
    }

    public void setCredit_restant(int credit_restant) {
        this.credit_restant = credit_restant;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
}
