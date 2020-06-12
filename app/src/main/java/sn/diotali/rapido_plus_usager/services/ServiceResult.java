package sn.diotali.rapido_plus_usager.services;

import java.io.Serializable;

/**
 * Created by Cheikhouna on 02/02/2018.
 */

public class ServiceResult implements Serializable{

    private String method;
    private int status;
    private String message;

    public ServiceResult()
    {
        super();
    }

    public ServiceResult(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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



    @Override
    public String toString() {
        return "status = " + status + ", message = " + message;
    }
}
