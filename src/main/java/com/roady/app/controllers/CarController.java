package com.roady.app.controllers;

import com.roady.app.entities.Car;
import com.roady.app.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    public void viewAllCarsList(Model model){
        List<Car> carList = carService.allCarsList();
        model.addAttribute("carList", carList);
    }


}
