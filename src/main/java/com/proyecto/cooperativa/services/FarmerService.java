package com.proyecto.cooperativa.services;

import com.proyecto.cooperativa.models.Farmer;
import com.proyecto.cooperativa.repositories.FarmerRepository;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.proyecto.cooperativa.models.Constants.*;

@Service
public class FarmerService {
    private static final Logger log = LoggerFactory.getLogger(FarmerService.class);

    @Autowired
    FarmerRepository farmerRepository;

    public List<Map<String, Object>> getFarmersList(String textToSeach) {
        return farmerRepository.getFarmersList(textToSeach);
    }

    public String create(@NonNull Farmer farmer) {
        final String CREATION_MESSAGE = SE_HA_DADO + ALTA + EL_AGRICULTOR;
        final String ERROR_MESSAGE = NO_SE_HA_PODIDO + ALTA + EL_AGRICULTOR;
        if (farmerRepository.createFarmer(farmer)) {
            return CREATION_MESSAGE;
        } else {
            return ERROR_MESSAGE;
        }
    }

    public String dropOut(@NonNull Farmer farmer) {
        final String CREATION_MESSAGE = SE_HA_DADO + BAJA + EL_AGRICULTOR;
        final String ERROR_MESSAGE = NO_SE_HA_PODIDO + BAJA + EL_AGRICULTOR;
        if (farmerRepository.dropOut(farmer)) {
            return CREATION_MESSAGE;
        } else {
            return ERROR_MESSAGE;
        }
    }

    public Farmer read(@NonNull Farmer farmer){
        return farmerRepository.read(farmer.getPersonId());
    }

}
