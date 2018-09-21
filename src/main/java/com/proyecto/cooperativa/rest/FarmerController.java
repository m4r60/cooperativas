package com.proyecto.cooperativa.rest;

import com.proyecto.cooperativa.models.Farmer;
import com.proyecto.cooperativa.repositories.FarmerRepository;
import com.proyecto.cooperativa.services.FarmerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FarmerController {
    private static final Logger log = LoggerFactory.getLogger(FarmerRepository.class);

    @Autowired
    FarmerService farmerService;

    private static final String SLASH = "/";
    private static final String FARMER_LIST = "farmerList";
    private static final String SAVE_FARMER = "saveFarmer";
    private static final String DROP_OUT_FARMER = "dropOutFarmer";

    @PostMapping(SLASH + FARMER_LIST)
    public Model farmerList (@RequestParam String textToSearch, Model model){
        model.addAttribute(FARMER_LIST,farmerService.getFarmersList(textToSearch));
        return model;
    }

    @PostMapping(SLASH + SAVE_FARMER)
    public Model save (@RequestParam Farmer farmer, Model model){
        model.addAttribute(FARMER_LIST, farmerService.createFarmer(farmer));
        return model;
    }

    @PostMapping(SLASH + DROP_OUT_FARMER)
    public Model dropOut (@RequestParam Farmer farmer, Model model){
        model.addAttribute(FARMER_LIST, farmerService.dropOutFarmer(farmer));
        return model;
    }

}
