package Ironhack.CapstoneProject.models.Users;

import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Users.Address.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class AccountHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private Integer age;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;
    @NotNull
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

    @OneToOne
    private User user;


    public AccountHolder(String name, Integer age, String email, String phoneNumber, Address address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public AccountHolder() {
    }

    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> accountList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
