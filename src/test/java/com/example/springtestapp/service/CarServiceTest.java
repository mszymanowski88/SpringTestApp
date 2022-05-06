package com.example.springtestapp.service;


import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {


    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;


  @Test
    public void shoudSaveTheCar()
    {
        System.out.println(carService.getAllCars());
        System.out.println(carRepository.findAll());
        Car car1 = new Car();
        car1.setBrand("Skoda");
        car1.setModel("Octavia");
        car1.setColor("Yellow");
        car1.setYear(2019);


        when(carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(car1);

        Car created = carService.saveCar(car1);
        System.out.println(created);

        assertThat(created.getBrand()).isSameAs(car1.getBrand());
        verify(carRepository).save(car1);

    }


    @Test
    public void shouldReturnListOfCars()
    {
        Car car1 = new Car();
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



        given(carRepository.findAll()).willReturn(testList);

         List<Car> expected = carService.getAllCars();

        assertEquals(expected,testList);



    }

    @Test
    public void shouldReturnCarByID()
    {
        Car car1 = new Car();
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

        given(carRepository.findById(1L)).willReturn(java.util.Optional.of(car1));

        Car expected = carService.findCarById(1L);

        assertEquals(expected,car1);
//        assertEquals(expected,car1);

    }

    @Test
    public void shouldDeleteCarById()
    {
        Car car1 = new Car();
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

       when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car1));

  carService.removeCarById(car1.getId());

//       verify(carRepository).deleteById(car1.getId());

        verify(carRepository).deleteById(car1.getId());

    }

//    @Test
    @Test(expected = RuntimeException.class)
    public void shouldReturnExceptionWhenUserDoesNotExist()
    {
        Car car1 = new Car();
        car1.setBrand("Skoda");
        car1.setModel("Octavia");
        car1.setColor("Yellow");
        car1.setYear(2019);



        given(carRepository.findById(anyLong())).willReturn(null);
        carService.removeCarById(car1.getId());

    }


    @Test
    public void shouldUpdateTheUser()
    {

        Car car = new Car();
        car.setId(1L);
        car.setBrand("Skoda");
        car.setModel("Octavia");
        car.setColor("Yellow");
        car.setYear(2019);

        Car testCar = new Car();
        testCar.setBrand("VW");
        testCar.setModel("Golf");
        testCar.setColor("Red");
        testCar.setYear(2022);

        carRepository.save(car);
        carRepository.save(testCar);

        System.out.println(car);

        given(carRepository.findById(car.getId())).willReturn(java.util.Optional.of(car));
        carService.updateCar(car,"Bmw","1","Green",2021);

        assertEquals("Bmw",car.getBrand());
//        assertEquals("Audi",car.getBrand());
        verify(carRepository).save(testCar);
        verify(carRepository).findById(car.getId());

        System.out.println(car);

    }


    @Test
    public void shouldReturnListOfCarsByColor()
    {
        Car car1 = new Car();
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



        given(carRepository.findCarsByColor("Red")).willReturn(testList);

        List<Car> expected = carRepository.findCarsByColor("Red");

        assertEquals(expected,testList);
        assertEquals(expected.size(),2);



     verify(carRepository).findCarsByColor("Red");



    }

}