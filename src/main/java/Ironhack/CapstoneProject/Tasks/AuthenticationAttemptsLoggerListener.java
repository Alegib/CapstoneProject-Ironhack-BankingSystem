
package Ironhack.CapstoneProject.Tasks;


import Ironhack.CapstoneProject.models.Users.Login;
import Ironhack.CapstoneProject.models.repositories.LoginRepository;
import Ironhack.CapstoneProject.models.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAttemptsLoggerListener implements ApplicationListener<AbstractAuthenticationEvent> {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationAttemptsLoggerListener.class);

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * *
    Detects all login attempts and stores them into the Login Repository regardless are successful
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * * */


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