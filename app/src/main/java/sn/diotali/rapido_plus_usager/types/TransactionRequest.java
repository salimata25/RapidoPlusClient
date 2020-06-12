package sn.diotali.rapido_plus_usager.types;

import java.util.ArrayList;
import java.util.List;

import sn.diotali.rapido_plus_usager.services.MethodParams;

public class TransactionRequest extends MethodParams {

    private String token;
    private String terminalNumber;

    private  Integer montantTotal;

    private String transactionType;
    private String modePaiement;
    private String moyenPaiement;
    private String emailReception;
    private String moyenReception = "autre";
    private String telReception;
    private String telephonePaiement;
    private String codeAutoristionPaiement;

    private List<Quittance> infoQuittance;
    private List<Timbre> panierTimbres;

    private String autreMontant = "";

    public TransactionRequest() {
        super();
        this.panierTimbres = new ArrayList<>();
        this.infoQuittance = new ArrayList<>();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public List<Quittance> getInfoQuittance() {
        return infoQuittance;
    }

    public void addQuittance(Quittance quittance) {
        this.infoQuittance = new ArrayList<>();
        this.infoQuittance.add(quittance);
    }

    public void setInfoQuittance(List<Quittance> infoQuittance) {
        this.infoQuittance = infoQuittance;
    }


    public List<Timbre> getPanierTimbres() {
        return panierTimbres;
    }

    public void setPanierTimbres(List<Timbre> panierTimbres) {
        this.panierTimbres = panierTimbres;
    }

    public Integer getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Integer montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getAutreMontant() {
        return autreMontant;
    }

    public void setAutreMontant(String autreMontant) {
        this.autreMontant = autreMontant;
    }

    public String getEmailReception() {
        return emailReception;
    }

    public void setEmailReception(String emailReception) {
        this.emailReception = emailReception;
    }

    public String getMoyenReception() {
        return moyenReception;
    }

    public void setMoyenReception(String moyenReception) {
        this.moyenReception = moyenReception;
    }

    public String getTelReception() {
        return telReception;
    }

    public void setTelReception(String telReception) {
        this.telReception = telReception;
    }

    public String getTelephonePaiement() {
        return telephonePaiement;
    }

    public void setTelephonePaiement(String telephonePaiement) {
        this.telephonePaiement = telephonePaiement;
    }

    public String getCodeAutoristionPaiement() {
        return codeAutoristionPaiement;
    }

    public void setCodeAutoristionPaiement(String codeAutoristionPaiement) {
        this.codeAutoristionPaiement = codeAutoristionPaiement;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "token='" + token + '\'' +
                ", terminalNumber='" + terminalNumber + '\'' +
                ", montantTotal=" + montantTotal +
                ", transactionType='" + transactionType + '\'' +
                ", modePaiement='" + modePaiement + '\'' +
                ", moyenPaiement='" + moyenPaiement + '\'' +
                ", emailReception='" + emailReception + '\'' +
                ", moyenReception='" + moyenReception + '\'' +
                ", telReception='" + telReception + '\'' +
                ", telephonePaiement='" + telephonePaiement + '\'' +
                ", codeAutoristionPaiement='" + codeAutoristionPaiement + '\'' +
                ", infoQuittance=" + infoQuittance +
                ", panierTimbres=" + panierTimbres +
                '}';
    }
}

