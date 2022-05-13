package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaveCarTest {

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
    public void shouldSaveTheCar() {
        setUp();

        Car expected = new Car();
        expected.setId(1L);
        expected.setBrand("Skoda");
        expected.setModel("Octavia");
        expected.setColor("Yellow");
        expected.setYear(2019);


        when(carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(expected);


        Car created = carService.saveCar(expected);

        assertThat(expected).isSameAs(created);



    }


}

