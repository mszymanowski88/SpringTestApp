package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class UpdateCarTest {

    //System under Test
    private CarService carService;

    //Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);

    }
//
//    @Test
//    public void shouldUpdateCarTest() {
//        setUp();
//
//        System.out.println();
//        Car expected = new Car();
//        expected.setId(1L);
//        expected.setBrand("Skoda");
//        expected.setModel("Octavia");
//        expected.setColor("Yellow");
//        expected.setYear(2019);
//
//        Mockito.when(carRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
//
//        carService.updateCar(expected, "Skoda", "octavia", "Brown", 2020);
//
//        assertEquals(expected.getColor(), "Brown");
//
//
//    }

    @Test
    public void shouldUpdateCarTest() {
        setUp();

        System.out.println();

        Car  testCar = new Car();
       testCar.setId(1L);
        testCar.setBrand("Skoda");
        testCar.setModel("Octavia");
        testCar.setColor("Brown");
        testCar.setYear(2019);


        Car expected = new Car();
        expected.setId(1L);
        expected.setBrand("Skoda");
        expected.setModel("Octavia");
        expected.setColor("Yellow");
        expected.setYear(2019);

        Mockito.when(carRepository.findById(testCar.getId())).thenReturn(Optional.of(testCar));

        carService.updateCar(testCar.getId(),expected);

        assertEquals(testCar.getColor(), "Yellow");


    }
}
