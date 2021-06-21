package com.example.jwtdemo.bootstrap;

import com.example.jwtdemo.domain.model.Reagent;
import com.example.jwtdemo.domain.security.Authority;
import com.example.jwtdemo.domain.security.User;
import com.example.jwtdemo.repositories.sdjpa.ReagentJPARepo;
import com.example.jwtdemo.repositories.sdjpa.AuthorityJPARepo;
import com.example.jwtdemo.repositories.sdjpa.UserJPARepo;
import com.example.jwtdemo.service.ReagentService;
import com.example.jwtdemo.service.AuthorityService;
import com.example.jwtdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ReagentService reagentService;
    private final ReagentJPARepo reagentJPARepo;

    private final UserJPARepo userJPARepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthorityJPARepo authorityJPARepo;
    private final AuthorityService authorityService;

    @Override
    public void run(String... args) {
        if (reagentService.findAll() == null || reagentService.findAll().isEmpty()){
            log.info("=========== Reagent database empty =====================");

            Reagent water = Reagent.builder().CAS_id("7732-18-5").chemicalName("Water").build();
            Reagent ammonia = Reagent.builder().CAS_id("7664-41-7").chemicalName("Ammonia").build();

            reagentJPARepo.save(water);
            reagentJPARepo.save(ammonia);

            log.info("Saved " + reagentService.findAll().size() + " reagents");
        }

        if (authorityService.findAll() == null || authorityService.findAll().isEmpty()){
            log.info("=========== User database empty =====================");

            // have to use ROLE_ADMIN and ROLE_USER
            Authority adminAuthority = authorityJPARepo.save(Authority.builder().role(Authority.ROLE_ADMIN).build());
            Authority userAuthority = authorityJPARepo.save(Authority.builder().role(Authority.ROLE_USER).build());
            log.info("Authorities added: " + authorityJPARepo.findAll().size());

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .authority(adminAuthority)
                    .build();
            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user123"))
                    .authority(userAuthority)
                    .build();

            userJPARepo.save(admin);
            userJPARepo.save(user);

            log.info("Saved " + userService.findAll().size() + " users");
        }
    }
}
