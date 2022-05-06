package com.example.springtestapp.service;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FindCarByIdTest {

    //System under test
   CarService carService;

   //Mock
    CarRepository carRepository;

    @BeforeEach
    public void setUp()
    {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);

    }


    @Test
    public void shouldFindById()
    {

        setUp();

        Car expected = new Car();
        expected.setId(1L);
        expected.setBrand("Skoda");
        expected.setModel("Octavia");
        expected.setColor("Yellow");
        expected.setYear(2019);

        Mockito.when(carRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        Car actual = carService.findCarById(expected.getId());


        assertEquals(expected,actual);

    }

}



//    @Mock
//    private CarRepository carRepository;
//
//    @InjectMocks
//    private CarServiceImpl carService;
//
//    @Test
//    public void shouldReturnCarByID()
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
//        List<Car> testList =  new ArrayList<>(Arrays.asList(car1,car2));
//
//        given(carRepository.findById(1L)).willReturn(java.util.Optional.of(car1));
//
//        Car expected = carService.findCarById(1L);
//
//        assertEquals(expected,car1);
////        assertEquals(expected,car1);
//
//    }
//


//    private CarServiceImpl carService;
//
//    @BeforeEach
//    public void setUpMock()
//    {
//
//        CarRepository carRepository = mock(CarRepository.class);
//        Car car1 = new Car();
//        car1.setId(1L);
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//
//
//
//        doReturn(Optional.ofNullable(car1)).when(carRepository).findById(1L);
//
//        carService = new CarServiceImpl(carRepository);
//
//
//    }
//
//    @Test
//    public void shouldFindCarById()
//    {
//
//
//
//        setUpMock();
//
//
//
//        //when
//        Car actual = carService.findCarById(1L);
//
//
//        //then
//        assertEquals(1,actual.getId().longValue());
//
//
//    }
//
//    @Test
//    public void shouldFindSkodaAsId1()
//    {
//
//        setUpMock();
//
//        //when
//        Car actual = carService.findCarById(1L);
//
//
//        //then
//        assertEquals("Skoda",actual.getBrand());
//
//
//    }
//


