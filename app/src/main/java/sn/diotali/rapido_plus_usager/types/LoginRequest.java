package sn.diotali.rapido_plus_usager.types;

import sn.diotali.rapido_plus_usager.services.MethodParams;
import sn.diotali.rapido_plus_usager.utils.DiotaliUtils;

public class LoginRequest  extends MethodParams {

    private String emailOrPhone;
    private String password;
    private String terminalNumber;



    public LoginRequest(String emailOrPhone, String password){
        super();
        this.emailOrPhone = emailOrPhone;
        this.password = password;
        this.terminalNumber = DiotaliUtils.getSerialNumber();
    }

    public LoginRequest(){
        super();
    }

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "emailOrPhone='" + emailOrPhone + '\'' +
                ", password='" + password + '\'' +
                ", terminalNumber='" + terminalNumber + '\'' +
                '}';
    }
}
