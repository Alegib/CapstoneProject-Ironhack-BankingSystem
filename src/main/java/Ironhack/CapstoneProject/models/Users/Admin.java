package Ironhack.CapstoneProject.models.Users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Admin extends User {
    @NotEmpty
    private String name;

    public Admin(String username, String password, String name) {
        super(username, password);
        this.name = name;
    }

    public Admin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
