package com.proyecto.cooperativa.services;

import com.proyecto.cooperativa.models.Farmer;
import com.proyecto.cooperativa.repositories.FarmerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FarmerService {

    @Autowired
    FarmerRepository farmerRepository;

    public List<Map<String, Object>> getFarmersList(String textToSeach){
        return farmerRepository.getFarmersList(textToSeach);
    }

    public String createFarmer(@NonNull Farmer farmer){
        final String CREATION_MESSAGE = "Se ha podido crear el agricultor";
        final String ERROR_MESSAGE = "No se ha podido crear el Agricultor";
        if(farmerRepository.createFarmer(farmer)){
            return CREATION_MESSAGE;
        } else {
            return ERROR_MESSAGE;
        }
    }

}
