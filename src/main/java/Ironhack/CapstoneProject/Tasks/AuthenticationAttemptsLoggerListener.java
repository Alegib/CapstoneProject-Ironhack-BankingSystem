
package Ironhack.CapstoneProject.Tasks;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Date;


import Ironhack.CapstoneProject.Services.EmailService.EmailService;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.Checking;
import Ironhack.CapstoneProject.models.Accounts.Savings;
import Ironhack.CapstoneProject.models.Accounts.StudentChecking;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.Login;
import Ironhack.CapstoneProject.models.repositories.AccountHolderRepository;
import Ironhack.CapstoneProject.models.repositories.AccountRepository;
import Ironhack.CapstoneProject.models.repositories.LoginRepository;
import Ironhack.CapstoneProject.models.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

@Component
@EnableScheduling
@ComponentScan
public class ScheduledTask implements ApplicationListener<AbstractAuthenticationEvent> {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    UserRepository userRepository;


    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(fixedRateString = "PT05S")
    public void reportCurrentTime() {

//        log.info("The time is now {}", dateFormat.format(new Date()));


    }


    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {

        Authentication auth = event.getAuthentication();

        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        String ipAddress = details.getRemoteAddress();
        String username = auth.getName();


        if (event instanceof AbstractAuthenticationFailureEvent) {
            log.warn("Unsuccessful authentication attempted from: " + ipAddress);
            Login login = new Login(ipAddress);
            loginRepository.save(login);
        } else {
            log.info("Successful authentication attempted from: " + ipAddress);
            Login login = new Login(ipAddress, userRepository.findByUsername(auth.getName()).get());
            loginRepository.save(login);

        }
    }
}

