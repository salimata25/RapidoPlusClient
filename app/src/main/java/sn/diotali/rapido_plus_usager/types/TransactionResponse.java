package sn.diotali.rapido_plus_usager.types;

import java.util.List;

import sn.diotali.rapido_plus_usager.services.ServiceResult;

public class TransactionResponse extends ServiceResult {

    private int status;
    private String message;

    List<HistoriqueAchat> listUsers;

    HistoriqueAchat data;

    public TransactionResponse() {
        super();
    }

    public TransactionResponse(int status, String message) {
        super(status, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HistoriqueAchat> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<HistoriqueAchat> listUsers) {
        this.listUsers = listUsers;
    }

    public HistoriqueAchat getData() {
        return data;
    }

    public void setData(HistoriqueAchat data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", listUsers=" + listUsers +
                ", data=" + data +
                '}';
    }
}

