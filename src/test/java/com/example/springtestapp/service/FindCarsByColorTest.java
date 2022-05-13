package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FindCarsByColorTest {

    //System uunder test
    private CarService carService;

    //Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);


    }

    @Test
    public void shouldReturnListOfCarsByColor() {
        setUp();

        Car car2 = new Car();
        car2.setBrand("VW");
        car2.setModel("Golf");
        car2.setColor("Red");
        car2.setYear(2022);

        List<Car> testList = new ArrayList<>(Arrays.asList(car2));


        Mockito.when(carRepository.findCarsByColor("Red")).thenReturn(testList);

        List<Car> expected = carRepository.findCarsByColor("Red");

        assertEquals(expected, testList);
        assertEquals(expected.size(), 1);


        verify(carRepository).findCarsByColor("Red");

    }


}
