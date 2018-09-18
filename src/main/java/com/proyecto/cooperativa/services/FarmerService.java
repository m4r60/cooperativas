package com.proyecto.cooperativa.services;

import com.proyecto.cooperativa.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FarmerService {

    @Autowired
    FarmerRepository farmerRepository;

    public Map<String, Object> getFarmersList(String textToSeach){
        return farmerRepository.getFarmersList(textToSeach);
    }

}
