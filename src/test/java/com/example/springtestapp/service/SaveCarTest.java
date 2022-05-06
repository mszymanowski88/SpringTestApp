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
    public void setUp()
    {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);

    }

    @Test
    public void shouldSaveTheCar()
    {
        setUp();

        Car expected = new Car();
        expected.setId(1L);
        expected.setBrand("Skoda");
        expected.setModel("Octavia");
        expected.setColor("Yellow");
        expected.setYear(2019);


        when(carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(expected);
        System.out.println(expected);
        System.out.println(carService.getAllCars());

        Car created = carService.saveCar(expected);

        assertThat(expected).isSameAs(created);
//                assertThat(created.getBrand()).isSameAs(car1.getBrand());
//        assertThat(created.getBrand()).isSameAs("Skoda");
//        assertThat(created.getBrand()).isNotEqualTo("");
//        verify(carRepository).save(car1);
//


    }


}


//    @Mock
//    private CarRepository carRepository;
//
//    @InjectMocks
//    private CarServiceImpl carService;
//
//
//    @Test
//    public void shoudSaveTheCar()
//    {
//
//
//        Car car1 = new Car();
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//
//        when(carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(car1);
//
//        Car created = carService.saveCar(car1);
//        assertThat(created.getBrand()).isSameAs(car1.getBrand());
//        assertThat(created.getBrand()).isSameAs("Skoda");
//        assertThat(created.getBrand()).isNotEqualTo("");
//        verify(carRepository).save(car1);
//
//    }
//
//


//
//
//    @BeforeEach
//    public void setUp()
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
//        Car car2 = new Car();
//        car2.setId(2L);
//        car2.setBrand("VW");
//        car2.setModel("Golf");
//        car2.setColor("Red");
//        car2.setYear(2022);
//
//Car carToSave = new Car();
//        carToSave.setId(3L);
//        carToSave.setBrand("Fiat");
//        carToSave.setModel("Tipo");
//        carToSave.setColor("Silver");
//        carToSave.setYear(2021);
//
//
//        List<Car> testList =  new ArrayList<>();
//        testList.add(car1);
//        testList.add(car2);
////        doReturn(carToSave).when(carRepository.).save(carToSave);
////        doReturn(carToSave).when(carRepository).save(carToSave);
////
////        doReturn(carToSave).when(carRepository).save(ArgumentMatchers.any(Car.class));
//
//        when(carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(carToSave);
////        carService = new CarServiceImpl(carRepository);
//
//        when(carRepository.save(carToSave)).thenReturn(carToSave);
//        carService = new CarServiceImpl(carRepository);
//
//    }
//
//    @Test
//    public void shoudSaveTheCar1()
//    {
//        System.out.println(carService.getAllCars());
//        System.out.println(carRepository.findAll());
//        Car car1 = new Car();
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//
//        when(carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(car1);
//
//        Car created = carService.saveCar(car1);
//        System.out.println(created);
//
//        assertThat(created.getBrand()).isSameAs(car1.getBrand());
//        assertThat(carService.getAllCars().size()).isSameAs(1);
//        verify(carRepository).save(car1);
//
//    }
//
//
//    //
//    @Test
//    public void shouldSaveTheCar()
//    {
//
//
//        setUp();
//        System.out.println(carService.getAllCars());
//
//        Car carToSave = new Car();
//        carToSave.setId(3L);
//        carToSave.setBrand("Fiat");
//        carToSave.setModel("Tipo");
//        carToSave.setColor("Silver");
//        carToSave.setYear(2021);
//
////        when
//        carService.saveCar(carToSave);
////
////        System.out.println(carService.getAllCars());
//
//
////        assertThat(cc);
//
//        //then
//        assertEquals(3,carService.getAllCars().size());
//
//
//
//}

