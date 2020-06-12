package sn.diotali.rapido_plus_usager.types;

import sn.diotali.rapido_plus_usager.services.ServiceResult;
import sn.diotali.rapido_plus_usager.utils.DiotaliUtils;

public class User extends ServiceResult {

    private String token;

    private String firstName;
    private String lastName;
    private String address;
    private String matricule;
    private String phone;
    private String nin;

    private String codeActivation;

    private String login;
    private String newLogin;

    private String actuelMp;
    private String nouveauMp;

    private String password;
    private String newPassword;

    private String email;
    private String newEmail;

    private String role;
    private String type;
    private int status;

    private String  centre;
    private String  bureau;

    private String userNumber;

    private Integer balance;

    private String terminalNumber;

    private String message;

    public User(){
        super();
        this.terminalNumber = DiotaliUtils.getSerialNumber();
    }

    public User(int status, String message){
        super(status,message);
        this.terminalNumber = DiotaliUtils.getSerialNumber();
    }

    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
        this.terminalNumber = DiotaliUtils.getSerialNumber();
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getCodeActivation() {
        return codeActivation;
    }

    public void setCodeActivation(String codeActivation) {
        this.codeActivation = codeActivation;
    }

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }

    public String getActuelMp() {
        return actuelMp;
    }

    public void setActuelMp(String actuelMp) {
        this.actuelMp = actuelMp;
    }

    public String getNouveauMp() {
        return nouveauMp;
    }

    public void setNouveauMp(String nouveauMp) {
        this.nouveauMp = nouveauMp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", matricule='" + matricule + '\'' +
                ", phone='" + phone + '\'' +
                ", nin='" + nin + '\'' +
                ", codeActivation='" + codeActivation + '\'' +
                ", login='" + login + '\'' +
                ", newLogin='" + newLogin + '\'' +
                ", actuelMp='" + actuelMp + '\'' +
                ", nouveauMp='" + nouveauMp + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", email='" + email + '\'' +
                ", newEmail='" + newEmail + '\'' +
                ", role='" + role + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", centre='" + centre + '\'' +
                ", bureau='" + bureau + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", balance=" + balance +
                ", terminalNumber='" + terminalNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
