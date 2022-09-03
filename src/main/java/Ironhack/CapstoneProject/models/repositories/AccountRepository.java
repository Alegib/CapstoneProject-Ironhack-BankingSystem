package Ironhack.CapstoneProject.models.repositories;

import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findBySecretKey(String secretKey);

    List<Account> findByPrimaryOwnerId(Long id);


}
