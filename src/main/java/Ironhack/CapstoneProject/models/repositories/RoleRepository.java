package Ironhack.CapstoneProject.models.repositories;

import Ironhack.CapstoneProject.models.Users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
