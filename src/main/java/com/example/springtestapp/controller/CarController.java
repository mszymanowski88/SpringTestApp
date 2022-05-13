package com.example.springtestapp.controller;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.service.CarService;
import com.example.springtestapp.service.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {


    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Car> getAllCars() {
//            return carRepository.findAll();
        return carService.getAllCars();
    }

    @GetMapping(value = "/cars/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Car getCarById(@PathVariable(value = "id") long id) {
        return carService.findCarById(id);

    }

    @GetMapping(value = "/color/{color}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Car> getAllCarsByColor(@PathVariable(value = "color") String color) {

        return carService.findCarsByColor(color);
    }


    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Car createNewCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }


    @PutMapping(value = "/update/{id}/{brand}/{model}/{color}/{productionYear}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Car updateCar(@PathVariable(value = "id") long id, @PathVariable(value = "brand") String brand, @PathVariable(value = "model") String model, @PathVariable(value = "color") String color, @PathVariable(value = "productionYear") Integer productionYear) {

        return carService.updateCar(carService.findCarById(id), brand, model, color, 2023);

    }

    @PutMapping(value = "/colorupdate/{id}/{color}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Car changeColor(@PathVariable(value = "id") long id, @PathVariable(value = "color") String color) {

        return carService.changeColor(id, color);

    }


    @DeleteMapping("/cars/remove/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCar(@PathVariable(value = "id") Long id) throws EntityNotFoundException {

        carService.removeCarById(id);

        return "removed";

    }


}
