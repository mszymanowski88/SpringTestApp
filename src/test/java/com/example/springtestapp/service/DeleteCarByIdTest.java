package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCarByIdTest {


    //System under Test
    CarService carService;

    //Mock
    CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);


    }


    @Test
    public void shouldRemoveCarById() {
        setUp();

        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);

        Car expected = new Car();
        expected.setId(1L);
        expected.setBrand("Skoda");
        expected.setModel("Octavia");
        expected.setColor("Yellow");
        expected.setYear(2019);

        Mockito.when(carRepository.findById(expected.getId())).thenReturn(Optional.of(expected));


        carService.removeCarById(expected.getId());

        Mockito.verify(carRepository).deleteById(expected.getId());


    }

}
