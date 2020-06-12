package sn.diotali.rapido_plus_usager.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quittance implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String nin;
    private String address;

    public Quittance() { }

    public Quittance(String firstName, String lastName, String phone, String nin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.nin = nin;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Quittance{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", nin='" + nin + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
