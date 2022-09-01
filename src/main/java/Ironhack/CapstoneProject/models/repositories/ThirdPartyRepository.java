package Ironhack.CapstoneProject.models.repositories;

import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdPartyAccount, Long> {
}
