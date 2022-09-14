package com.roady.app.controllers;

import com.roady.app.entities.Car;
import com.roady.app.entities.User;
import com.roady.app.services.CarService;
import com.roady.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

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
            @RequestParam(name = "status", required = false) String status,
            Model model
    ){
        try{
            Long car_id = userController.currentUser.getCar().getId();
            String car_type =carService.getCarById(car_id).getCarType();
            String plate_Number =carService.getCarById(car_id).getPlateNumber();
            Integer available_seats =carService.getCarById(car_id).getAvailableSeats();

            model.addAttribute("status", status);
            model.addAttribute("carType", car_type);
            model.addAttribute("plateNumber", plate_Number);
            model.addAttribute("availableSeats", available_seats);
            model.addAttribute("car", 1);
        }catch (Exception e){
            model.addAttribute("car", null);
        }


        return "cars";
    }

    @GetMapping("/updateAvailableSeatsNumber")
    public String updateSeatNumbers(
            @RequestParam(name = "status", required = false) String status,
            Model model
    ){
        Long car_id = userController.currentUser.getCar().getId();
        String car_type =carService.getCarById(car_id).getCarType();
        String plate_Number =carService.getCarById(car_id).getPlateNumber();
        Integer available_seats =carService.getCarById(car_id).getAvailableSeats();

        model.addAttribute("status", status);
        model.addAttribute("carType", car_type);
        model.addAttribute("plateNumber", plate_Number);
        model.addAttribute("availableSeats", available_seats);

        return "updateAvailableSeatsNumber";
    }

    @PostMapping("/update_seats")
    public String updateSeats(Integer availableSeats){
        try{
            Car car = carService.getCarById(userController.currentUser.getCar().getId());
            System.out.println("Seats from input" + availableSeats);
            car.setAvailableSeats(availableSeats);
            carService.saveNewCar(car);

            return "redirect:cars?status=seats_updated";
        }catch(Exception e){
            return "redirect:cars?status=seats_update_failed";
        }

    }



    @PostMapping("/cars")
    public String addCar(String carType, String plateNumber, Integer availableSeats){

        try{

            Car car = new Car();
            car.setCarType(carType);
            car.setPlateNumber(plateNumber);
            car.setAvailableSeats(availableSeats);

            User user = userService.getUserById(userController.currentUser.getId());
            user.setCar(car);
            userController.currentUser=user;
            userService.saveUser(user);

//            carService.saveNewCar(car);
            return "redirect:cars?status=success";
        }catch (Exception e){
            return "redirect:cars?status=failed";
        }

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