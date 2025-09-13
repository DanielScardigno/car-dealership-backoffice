package com.cardealership.backoffice.dealership_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cardealership.backoffice.dealership_app.models.Car;
import com.cardealership.backoffice.dealership_app.repo.CarRepo;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CarRepo carRepo;
    
    @GetMapping
    public String home(Model model) {

        List<Car> cars = carRepo.findAll();
        model.addAttribute("cars", cars);

        return "pages/home";
    }
}