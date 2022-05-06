package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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
//
//    @Override
//    public void removeCarById(long id) {
//
//
//        carRepository.deleteById(id);
//
//    }


    @Override
    public Car updateCar(Car car, String brand, String model, String color, int year) {
        if (car.getId() == null || car.getId() == null) {

            throw new EntityNotFoundException("Car or ID must not be null!");
        }

        Optional<Car> carToUpdate = carRepository.findById(car.getId());
        if (carToUpdate.isEmpty()) {

            throw new EntityNotFoundException("Car with " + car.getId() + " does not exisit");
        }

        Car existingCarToUpdate = carToUpdate.get();
        existingCarToUpdate.setBrand(brand);
        existingCarToUpdate.setModel(model);
        existingCarToUpdate.setColor(color);
        existingCarToUpdate.setYear(year);

        return carRepository.save(existingCarToUpdate);




    }

    @Override
    public List<Car> findCarsByColor(String color) {

        return getAllCars().stream().filter(car -> color.equals(car.getColor())).collect(Collectors.toList());
    }

        @EventListener(ApplicationReadyEvent.class)
    public void test()
    {

                System.out.println(findCarById(4));




    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void test()
//    {
//
//        System.out.println("test");
//        for(Car car : carRepository.findAll())
//            System.out.println("test "+ car);
//
//
//
//
//    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void testUpdate()
//    {
//
//
//  updateCar(carRepository.getById(3L),"SkodaUp", "Scala", "Yellow", 2019);
//        System.out.println(carRepository.findById(3L).get());
//    }
}

//    @EventListener(ApplicationReadyEvent.class)
//    public void testUpdate()
//    {
//        Car car = new Car(1L,"Skoda","Octavia","Yellow",2019);
//        carRepository.save(car);
//
//
//
//}
