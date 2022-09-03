package Ironhack.CapstoneProject.models.repositories;

import Ironhack.CapstoneProject.models.Users.Login;
import Ironhack.CapstoneProject.models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query(nativeQuery = true, value = "SELECT user.username, login.ip_address FROM user JOIN login ON user.id = login.id ")
    List<String> findAllIpAddressByUsername();
}
