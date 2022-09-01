package Ironhack.CapstoneProject.models.Users.Address;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Address {

    @NotEmpty
    private String street;
    @NotEmpty
    private String number;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private String postalCode;

    public Address(String street, String city, String country, String number, String postalCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return number;
    }

    public void setHouseNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
