package Ironhack.CapstoneProject.Controllers;

import Ironhack.CapstoneProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

}
