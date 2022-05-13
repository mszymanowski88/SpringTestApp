package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ChangeColorTest {

    //System under test
   private CarService carService;

    //Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setUp()
    {
       carRepository = Mockito.mock(CarRepository.class);
       carService = new CarServiceImpl(carRepository);

    }

    @Test
    public void shouldChangeTheColor()
    {
        setUp();

        Car testCar= new Car();
        testCar.setId(1L);
        testCar.setBrand("Skoda");
        testCar.setModel("Octavia");
        testCar.setColor("Yellow");
        testCar.setYear(2019);

        String newColor = "Red";

        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(testCar));

        carService.changeColor(1L,newColor);

        assertEquals(testCar.getColor(),"Red");


    }
}
