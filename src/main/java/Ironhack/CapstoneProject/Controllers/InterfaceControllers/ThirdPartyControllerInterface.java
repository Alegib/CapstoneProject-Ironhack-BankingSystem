package Ironhack.CapstoneProject.Controllers.InterfaceControllers;

import Ironhack.CapstoneProject.DTO.ThirdPartyDTO;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;

import java.util.Map;


public interface ThirdPartyControllerInterface {
    ThirdPartyAccount createThirdParty(ThirdPartyDTO thirdPartyDTO);
    ThirdPartyAccount findById(Long id);
    ThirdPartyAccount findThirdPartyByName(Map<String, String> header);
}
