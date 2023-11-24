package ma.elgharbaoui.hospitalapp;

import ma.elgharbaoui.hospitalapp.entities.Patient;
import ma.elgharbaoui.hospitalapp.repository.PatientRepository;
import ma.elgharbaoui.hospitalapp.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HospitalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalAppApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Mohamed",new Date(),false,42));
            patientRepository.save(new Patient(null,"Imane",new Date(),true,98));
            patientRepository.save(new Patient(null,"Yassine",new Date(),true,342));
            patientRepository.save(new Patient(null,"Laila",new Date(),false,123));
        };
    }
   // @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {

            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user12");
            UserDetails ad = jdbcUserDetailsManager.loadUserByUsername("Admin2");

            if(u1 == null) {
                jdbcUserDetailsManager.createUser(User.withUsername("user12").password(passwordEncoder.encode("1234")).roles("USER").build());
            }
            if(ad == null) {
                jdbcUserDetailsManager.createUser(User.withUsername("Admin2").password(passwordEncoder.encode("1234")).roles("ADMIN", "USER").build());
            }
        };

    }

    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user22","1234","user@gmail.com","1234");
            accountService.addNewUser("admin22","1234","admin@gmail.com","1234");

            accountService.addRoleToUser("user22","USER");
            accountService.addRoleToUser("admin22","ADMIN");



        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

