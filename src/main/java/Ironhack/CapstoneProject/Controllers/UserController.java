package Ironhack.CapstoneProject.Controllers;



import Ironhack.CapstoneProject.Services.AccountHolderService;
import Ironhack.CapstoneProject.Services.UserService;
import Ironhack.CapstoneProject.models.Users.Admin;
import Ironhack.CapstoneProject.models.Users.Login;
import Ironhack.CapstoneProject.models.Users.User;
import Ironhack.CapstoneProject.models.repositories.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;



    @PostMapping("/create-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody Admin admin) {
        return userService.createAdmin(admin);
    }



    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }


    @DeleteMapping("/delete-user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@RequestHeader Map<String, String> header){
         userService.deleteUser(header);

    }


    @RequestMapping(value = "/all-logins", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Login> findAllLogins(){
        return userService.findAllLogins();

    }
    @RequestMapping(value = "/all-user-ipAddress", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String>findAllIpAddressByUsername(){
        return userService.findAllIpAddressByUsername();

    }






}
