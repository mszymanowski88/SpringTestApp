package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;


    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car findCarById(long id) {

        return carRepository.findById(id).get();

    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void removeCarById(long id) throws com.example.springtestapp.service.EntityNotFoundException {
        if (carRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Car with ID " + id + " does not exsist");
        }

        carRepository.deleteById(id);

    }



    @Override
    public Car updateCar(Long carIdToUpdateCar, Car car) {
        if (carIdToUpdateCar == null || carIdToUpdateCar == 0) {

            throw new EntityNotFoundException("Car or ID must not be null!");
        }

        Optional<Car> carToUpdate = carRepository.findById(carIdToUpdateCar);
        if (carToUpdate.isEmpty()) {

            throw new EntityNotFoundException("Car with " + car.getId() + " does not exisit");
        }

        Car existingCarToUpdate = carToUpdate.get();
        existingCarToUpdate.setBrand(car.getBrand());
        existingCarToUpdate.setModel(car.getModel());
        existingCarToUpdate.setColor(car.getColor());
        existingCarToUpdate.setYear(car.getYear());

        return carRepository.save(existingCarToUpdate);


    }




    @Override
    public List<Car> findCarsByColor(String color) {

        return getAllCars().stream().filter(car -> color.equals(car.getColor())).collect(Collectors.toList());
    }

    @Override
    public Car changeColor(long id, String color) {
        if (carRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Car with ID " + id + " does not exsist");
        }


        Car existingCarToUpdate = carRepository.findById(id).get();

        existingCarToUpdate.setColor(color);


        return carRepository.save(existingCarToUpdate);
    }



}


