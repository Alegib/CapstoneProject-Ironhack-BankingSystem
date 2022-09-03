package Ironhack.CapstoneProject.models.repositories;

import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdPartyAccount, Long> {

    @Query("SELECT id FROM ThirdPartyAccount WHERE hashKey = :hashKey")
    Long findByHashKey(@Param(value = "hashKey")  String hashKey);

    Optional<Object> findByName(String name);
}
