package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CarService {

    List<Car> getAllCars();

    Car findCarById(long id);

    Car saveCar (Car car);

    void removeCarById(long id);


    Car updateCar(Car car, String brand, String model, String color, int year);

    List<Car> findCarsByColor(String color);

}
