
package Ironhack.CapstoneProject.models.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Login{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipAddress;
    private LocalDateTime dateTime;
    @ManyToOne
    @JsonIgnore
    private User user;


    public Login(String ipAddress, User user) {
        this.user = user;
        this.ipAddress = ipAddress;
        setDateTime();
    }

    public Login(String ipAddress) {
        this.ipAddress = ipAddress;
        setDateTime();
    }

    public Login() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime() {
        this.dateTime = LocalDateTime.now();
    }
}


