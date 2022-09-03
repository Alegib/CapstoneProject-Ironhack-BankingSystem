package Ironhack.CapstoneProject.Services;


import Ironhack.CapstoneProject.models.Users.Admin;
import Ironhack.CapstoneProject.models.Users.Login;
import Ironhack.CapstoneProject.models.Users.Role;
import Ironhack.CapstoneProject.models.Users.User;
import Ironhack.CapstoneProject.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    RoleRepository roleRepository;



    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User createUser(User user){
        return userRepository.save(user);
    }


    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin = userRepository.save(admin);
        Role role = roleRepository.save(new Role("ADMIN", admin));
        return admin;
    }

    public void deleteUser(Map<String, String> header){
        User user = userRepository.findByUsername(header.get("username")).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        userRepository.delete(user);

    }

    public List<Login> findAllLogins(){
        return loginRepository.findAll();
    }

    public List<String>findAllIpAddressByUsername(){
        return loginRepository.findAllIpAddressByUsername();
    }

}
