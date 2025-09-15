package com.cardealership.backoffice.dealership_app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.cardealership.backoffice.dealership_app.models.Car;
import com.cardealership.backoffice.dealership_app.repo.CarRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarRepo carRepo;
    
    @GetMapping
    public String index(Model model) {

        model.addAttribute("cars", carRepo.findAll());

        return "cars/index";
    }

    @GetMapping("/{id}")
    public String view(Model model, @PathVariable Integer id) {

        Optional<Car> carOptional = carRepo.findById(id);

        if (carOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There's no cars with id: " + id);
        }

        model.addAttribute("car", carOptional.get());
        model.addAttribute("cars", carRepo.findAll());

        return "cars/view";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("car", new Car());

        return "cars/create";
    }

    @PostMapping("/create")
    public String store(Model model, @Valid @ModelAttribute("car") Car formCar, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "cars/create";
        } else {
            carRepo.save(formCar);
            return "redirect:/";
        }
    }
}