package Ironhack.CapstoneProject.Services;

import Ironhack.CapstoneProject.DTO.ThirdPartyDTO;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import Ironhack.CapstoneProject.models.repositories.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class ThirdPartyService {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public ThirdPartyAccount createThirdParty(ThirdPartyDTO thirdPartyDTO){
        ThirdPartyAccount thirdPartyAccount = new ThirdPartyAccount(thirdPartyDTO.getName());
        return thirdPartyRepository.save(thirdPartyAccount);
    }

    public ThirdPartyAccount findById(Long id){
        return thirdPartyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party Account not found."));
    }

    public ThirdPartyAccount findByName(Map<String, String> header){
        return (ThirdPartyAccount) thirdPartyRepository.findByName(header.get("name")).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party Account not found."));
    }

}
