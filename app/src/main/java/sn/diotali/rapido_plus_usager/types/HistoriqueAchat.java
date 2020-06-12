package sn.diotali.rapido_plus_usager.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoriqueAchat implements Serializable {




    private String transactionNumber;
    private String transactionType;
    private String paymentType;
    private Integer amount;
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateVendue;
    //@JsonFormat( pattern = "dd/MM/yyyy HH:mm:ss")
    private Date transactionDate =  new Date();

    //AGENT CONSOMMATEUR
    private String centre;
    private String bureau;
    private String address;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateConsommee;


    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateVendue() {
        return dateVendue;
    }

    public void setDateVendue(Date dateVendue) {
        this.dateVendue = dateVendue;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getBureau() {
        return bureau;
    }

    public void setBureau(String bureau) {
        this.bureau = bureau;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateConsommee() {
        return dateConsommee;
    }

    public void setDateConsommee(Date dateConsommee) {
        this.dateConsommee = dateConsommee;
    }

    public HistoriqueAchat(String transactionNumber, String transactionType, String paymentType, Integer amount, String status, Date transactionDate, Date dateVendue) {
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.transactionDate = transactionDate;
        this.dateVendue = dateVendue;
    }

    public HistoriqueAchat(String transactionType, Integer amount, String status, Date transactionDate) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    public HistoriqueAchat(String transactionNumber, String transactionType, String paymentType, Integer amount, String status, Date dateVendue, Date transactionDate, String centre, String bureau, String address, Date dateConsommee) {
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.dateVendue = dateVendue;
        this.transactionDate = transactionDate;
        this.centre = centre;
        this.bureau = bureau;
        this.address = address;
        this.dateConsommee = dateConsommee;
    }

    public HistoriqueAchat() {
    }




}
