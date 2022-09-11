package com.roady.app.controllers;

import com.roady.app.entities.Car;
import com.roady.app.services.CarService;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserController userController;
//    @GetMapping
//    public String viewAllCarsList(Model model){
//        List<Car> carList = carService.allCarsList();
//        model.addAttribute("carList", carList);
//        return "allCars";
//    }

    @GetMapping("/cars")
    public String showYourCarsPage(
            Model model
    ){
//        userController.currentUser.getId();

        return "cars";
    }

    @PostMapping("/cars")
    public String addCar(String carType, String plateNumber){
        Car car = new Car();
        car.setCarType(carType);
        car.setPlateNumber(plateNumber);
        car.setUser(userController.currentUser);
        carService.saveNewCar(car);
        return "redirect:driver_today";
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
