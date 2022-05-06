package com.example.springtestapp.service;


import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.*;
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
    public void setUp()
    {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);

    }

    @Test
    public void shouldReturnListOfCars()
    {
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

        List<Car> testList =  new ArrayList<>();
        testList.add(expected2);
        testList.add(expected2);

        Mockito.when(carRepository.findAll()).thenReturn(testList);
        List<Car> expectedList = carService.getAllCars();


        assertEquals(expectedList,testList);

    }

}


//    @Mock
//    private CarRepository carRepository;
//
//    @InjectMocks
//    private CarServiceImpl carService;





//    @Test
//    public void shouldReturnListOfCars()
//    {
//        Car car1 = new Car();
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//        Car car2 = new Car();
//        car2.setBrand("VW");
//        car2.setModel("Golf");
//        car2.setColor("Red");
//        car2.setYear(2022);
//
//        List<Car> testList =  new ArrayList<>(Arrays.asList(car1,car2));
//
//
//
//        given(carRepository.findAll()).willReturn(testList);
//
//        List<Car> expected = carService.getAllCars();
//
//        assertEquals(expected,testList);
//
//
//
//    }





//    private CarServiceImpl carService;
//
//    @BeforeEach
//    public void setUp()
//    {
//
//        CarRepository carRepository = mock(CarRepository.class);
//        Car car1 = new Car();
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//        Car car2 = new Car();
//        car2.setBrand("VW");
//        car2.setModel("Golf");
//        car2.setColor("Red");
//        car2.setYear(2022);
//
//        List<Car> testList =  new ArrayList<>();
//        testList.add(car1);
//        testList.add(car2);
//        doReturn(testList).when(carRepository).findAll();
//
//        carService = new CarServiceImpl(carRepository);
//
//
//    }
//
//
//    @Test
//    public void shouldReturnSameList()
//    {
//
//        setUp();
//
//        System.out.println(carService.getAllCars());
//        Car car1 = new Car();
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//        Car car2 = new Car();
//        car2.setBrand("VW");
//        car2.setModel("Golf");
//        car2.setColor("Red");
//        car2.setYear(2022);
//
//        List<Car> expected = Arrays.asList(car1,car2);
//
//
//        //when
//        List<Car> actual = carService.getAllCars();
//
//
//        //then
//        assertEquals(expected,actual);
//
//    }
//
//
//    @Test
//    public void shouldReturnPropoperSizeOfList()
//    {
//
//        setUp();
//
//        //when
//        List<Car> actual = carService.getAllCars();
//
//
//        //then
//        assertEquals(2,actual.size());
//
//    }
//
//    @Test
//    public void shouldFindCarById()
//    {
////
////        setUp();
////
////        //when
////        Car actual = carService.findCarById(1);
////        System.out.println(actual);
////
////        //then
////
////    assertEquals(1,actual.getId().longValue());
//
//
//        setUp();
//
//        //when
//        Car actual = carService.findCarById(1);
//
//
//        //then
//        assertEquals(actual.getId(), actual.getId());
//
//
//
//
//    }





