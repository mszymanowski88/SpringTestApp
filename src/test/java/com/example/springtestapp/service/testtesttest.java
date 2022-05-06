package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class testtesttest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDeleteCarById()
    {


        Car car1 = new Car();
        car1.setId(1L);
        car1.setBrand("Skoda");
        car1.setModel("Octavia");
        car1.setColor("Yellow");
        car1.setYear(2019);

        Car car2 = new Car();
        car2.setBrand("VW");
        car2.setModel("Golf");
        car2.setColor("Red");
        car2.setYear(2022);
        List<Car> testList =  new ArrayList<>(Arrays.asList(car1,car2));




        when(carRepository.findById(car1.getId())).thenReturn(java.util.Optional.of(car1));
        System.out.println(carService.getAllCars());
        carService.removeCarById(1L);

//       verify(carRepository).deleteById(car1.getId());

        verify(carRepository).deleteById(car1.getId());

    }


}
