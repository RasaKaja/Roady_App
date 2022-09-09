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

//    @GetMapping
//    public String viewAllCarsList(Model model){
//        List<Car> carList = carService.allCarsList();
//        model.addAttribute("carList", carList);
//        return "allCars";
//    }

    @GetMapping("/cars")
    public String showYourCarsPage(){
        return "cars";
    }

    @GetMapping("/driver_today")
    public String userIsDriver(){
        return "driver_today";
    }

    @GetMapping("/transport_offer")
    public String showTransportOfferingPage(){
        return "transport_offer";
    }


}
