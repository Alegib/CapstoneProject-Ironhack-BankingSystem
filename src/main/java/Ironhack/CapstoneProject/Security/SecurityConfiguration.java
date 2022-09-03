
package Ironhack.CapstoneProject.Security;

import Ironhack.CapstoneProject.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {

        return authConf.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()

                .mvcMatchers(HttpMethod.GET, "/all-accounts").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/create-account").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/change-status").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/user-accounts").hasAnyRole("ADMIN", "ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.GET, "/all-accountHolders").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/create-accountHolder").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/create-thirdParty").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/all-transactions/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/transaction/send").hasAnyRole("ADMIN", "ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.POST, "/transaction/third-party").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/user-transactions").hasAnyRole("ADMIN", "THIRD_PARTY")
                .mvcMatchers(HttpMethod.POST, "/find-transaction/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/modify-balance").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/find-account").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/find-thirdParty/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/find-thirdParty-name").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/delete-user").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/all-logins").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/all-user-ipAddress").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/delete-account").hasRole("ADMIN")

                .anyRequest().permitAll();


        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }


}




