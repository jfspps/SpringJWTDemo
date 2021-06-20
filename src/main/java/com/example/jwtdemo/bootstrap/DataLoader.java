package com.example.jwtdemo.bootstrap;

import com.example.jwtdemo.domain.model.Reagent;
import com.example.jwtdemo.repositories.sdjpa.ReagentJPARepo;
import com.example.jwtdemo.service.ReagentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ReagentService reagentService;

    private final ReagentJPARepo reagentJPARepo;

    @Override
    public void run(String... args) throws Exception {
        if (reagentService.findAll() == null || reagentService.findAll().isEmpty()){
            log.info("=========== Reagent database empty =====================");

            Reagent water = Reagent.builder().CAS_id("7732-18-5").chemicalName("Water").build();
            Reagent ammonia = Reagent.builder().CAS_id("7664-41-7").chemicalName("Ammonia").build();

            reagentJPARepo.save(water);
            reagentJPARepo.save(ammonia);

            log.info("Saved " + reagentService.findAll().size() + " reagents");
        }
    }
}
