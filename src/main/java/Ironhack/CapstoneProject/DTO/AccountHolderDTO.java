package Ironhack.CapstoneProject.DTO;

import Ironhack.CapstoneProject.models.Users.Address.Address;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AccountHolderDTO {
    @NotEmpty
    private String name;
    private int age;
    @NotNull
    private LocalDate dateOfBirth;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;
    @NotNull
    private Address address;
    private Address mailingAddress;

    public AccountHolderDTO() {
    }

    public AccountHolderDTO(String name, int age, LocalDate dateOfBirth, String username, String password, String email, String phoneNumber, Address address) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
