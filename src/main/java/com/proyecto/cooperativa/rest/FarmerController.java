package com.proyecto.cooperativa.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FarmerController {

    @GetMapping("/farmerList")
    public Model farmerList (Model model){
        return model;
    }
}
