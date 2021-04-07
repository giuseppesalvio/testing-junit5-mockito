package com.salvio.services;

import com.salvio.entitys.PolizzaCollegata;
import com.salvio.repository.PolizzaCollegataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolizzaCollegataService {

    private PolizzaCollegataRepository polizzaCollegataRepository;

    public PolizzaCollegataService(PolizzaCollegataRepository polizzaCollegataRepository) {
        this.polizzaCollegataRepository = polizzaCollegataRepository;
    }

    public List<PolizzaCollegata> getPolizzaCollegataDaService(String cfInput) {
      return polizzaCollegataRepository.getPolizzaCollegataDaRepo(cfInput);
    }
}
