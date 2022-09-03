package Ironhack.CapstoneProject.models.Users;

import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Users.Address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolder extends User{

    private String name;

    private Integer age;

    private LocalDate dateOfBirth;

    private String email;

    private String phoneNumber;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "mail_address_city")),
            @AttributeOverride(name = "country", column = @Column(name = "mail_address_country")),
            @AttributeOverride(name = "street", column = @Column(name = "mail_address_street")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mail_address_postalCode")),
            @AttributeOverride(name = "number", column = @Column(name = "mail_address_number"))
    })
    private Address mailingAddress;


    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accountList = new ArrayList<>();


    public AccountHolder(String name, Integer age, LocalDate dateOfBirth, String username, String password, String email, String phoneNumber, Address address) {
        super(username, password);
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public AccountHolder(String name, Integer age, String username, String password, LocalDate dateOfBirth, Address address) {
        super(username, password);
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


    public AccountHolder() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(Account account) {
        this.accountList.add(account);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }


}
