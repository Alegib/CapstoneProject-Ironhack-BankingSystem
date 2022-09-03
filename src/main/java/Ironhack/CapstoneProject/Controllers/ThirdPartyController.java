package Ironhack.CapstoneProject.Controllers;

import Ironhack.CapstoneProject.DTO.ThirdPartyDTO;
import Ironhack.CapstoneProject.Services.ThirdPartyService;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/create-thirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdPartyAccount createThirdParty(@RequestBody ThirdPartyDTO thirdPartyDTO){
        return thirdPartyService.createThirdParty(thirdPartyDTO);
    }

    @GetMapping("/find-thirdParty/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdPartyAccount findById(@PathVariable Long id){
        return thirdPartyService.findById(id);
    }

    @GetMapping("/find-thirdParty-name")
    @ResponseStatus(HttpStatus.OK)
    public ThirdPartyAccount findThirdPartyByName(@RequestHeader Map<String, String> header){
        return thirdPartyService.findByName(header);
    }

}
