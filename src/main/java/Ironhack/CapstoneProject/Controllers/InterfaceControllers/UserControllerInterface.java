package Ironhack.CapstoneProject.Controllers.InterfaceControllers;

import Ironhack.CapstoneProject.models.Users.Admin;
import Ironhack.CapstoneProject.models.Users.Login;
import Ironhack.CapstoneProject.models.Users.User;

import java.util.List;
import java.util.Map;


public interface UserControllerInterface {
    Admin createAdmin( Admin admin);
    User createUser(User user);
    void deleteUser(Map<String, String> header);
    List<Login> findAllLogins();
}
