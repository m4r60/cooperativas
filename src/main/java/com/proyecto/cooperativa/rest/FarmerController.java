package com.proyecto.cooperativa.rest;

import com.proyecto.cooperativa.models.Farmer;
import com.proyecto.cooperativa.services.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FarmerController {

    @Autowired
    FarmerService farmerService;

    private static final String SLASH = "/";
    private static final String FARMER_LIST = "farmerList";
    private static final String SAVE_FARMER = "saveFarmer";

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
}
