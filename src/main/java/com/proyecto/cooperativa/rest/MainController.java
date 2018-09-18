package com.proyecto.cooperativa.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model modelo) {
        modelo.addAttribute("mensaje", "hola mundo");
        return "index";
    }
}
