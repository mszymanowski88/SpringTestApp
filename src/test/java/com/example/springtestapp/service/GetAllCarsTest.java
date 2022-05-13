package com.example.springtestapp.service;


import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetAllCarsTest {


    //System under test
    private CarServiceImpl carService;

    //Mock
    private CarRepository carRepository;


    @BeforeEach
    public void setUp() {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);

    }

    @Test
    public void shouldReturnListOfCars() {
        setUp();

        Car expected = new Car();
        expected.setBrand("Skoda");
        expected.setModel("Octavia");
        expected.setColor("Yellow");
        expected.setYear(2019);

        Car expected2 = new Car();
        expected2.setBrand("VW");
        expected2.setModel("Golf");
        expected2.setColor("Red");
        expected2.setYear(2022);

        List<Car> testList = new ArrayList<>();
        testList.add(expected2);
        testList.add(expected2);

        Mockito.when(carRepository.findAll()).thenReturn(testList);
        List<Car> expectedList = carService.getAllCars();


        assertEquals(expectedList, testList);

    }

}






