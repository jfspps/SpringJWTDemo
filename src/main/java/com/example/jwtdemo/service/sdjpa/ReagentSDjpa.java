package com.example.jwtdemo.service.sdjpa;

import com.example.jwtdemo.domain.model.Reagent;
import com.example.jwtdemo.repositories.sdjpa.ReagentJPARepo;
import com.example.jwtdemo.service.ReagentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class ReagentSDjpa implements ReagentService {

    private final ReagentJPARepo reagentRepo;

    public ReagentSDjpa(ReagentJPARepo reagentRepo) {
        this.reagentRepo = reagentRepo;
    }

    @Override
    public Reagent save(Reagent object) {
        return reagentRepo.save(object);
    }

    @Override
    public Reagent findById(Long aLong) {
        return reagentRepo.findById(aLong).orElse(null);
    }

    @Override
    public Set<Reagent> findAll() {
        Set<Reagent> reagents = new HashSet<>();
        reagents.addAll(reagentRepo.findAll());
        return reagents;
    }

    @Override
    public void delete(Reagent objectT) {
        reagentRepo.delete(objectT);
    }

    @Override
    public void deleteById(Long aLong) {
        reagentRepo.deleteById(aLong);
    }

}
