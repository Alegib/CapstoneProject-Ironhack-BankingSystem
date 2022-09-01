package Ironhack.CapstoneProject.models.repositories;

import Ironhack.CapstoneProject.models.Users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
