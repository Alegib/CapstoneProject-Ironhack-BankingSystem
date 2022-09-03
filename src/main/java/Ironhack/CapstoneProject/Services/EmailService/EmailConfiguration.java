package Ironhack.CapstoneProject.Services.EmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfiguration {
    @Bean
    public SimpleMailMessage SimpleMailMessage() {
        return new SimpleMailMessage();
    }
}




