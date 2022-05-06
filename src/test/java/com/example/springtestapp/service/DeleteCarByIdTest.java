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
    public void setUp()
    {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);



    }



   @Test
    public void shouldRemoveCarById()
    {
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
//        Mockito.verify(carRepository,Mockito.times(1)).deleteById(expected.getId());

    }

}

//    @Mock
//    private CarRepository carRepository;
//
//    @InjectMocks
//    private CarServiceImpl carService;

//  /
//    @Test(expected = RuntimeException.class)
//    public void shouldReturnExceptionWhenUserDoesNotExist()
//    {
//
//        Car car1 = new Car();
//        car1.setId(1L);
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//        when(carRepository.findById(car1.getId())).thenReturn(Optional.of(car1));
//                carService.removeCarById(car1.getId());
//
//
////        when(carRepository.deleteById(car1.getId())).thenThrow(new EntityNotFoundException());
////        when(carRepository.findById(car1.getId())).thenReturn(java.util.Optional.of(car1));
////        System.out.println(carRepository.findById(1L));
////        given(carRepository.findById(anyLong())).willReturn(null);
////        System.out.println(carRepository.findById(1L));
////        carService.removeCarById(car1.getId());
//
//    }

//    private CarServiceImpl carService;
//    private Object EntityNotFoundException;
//
//    @BeforeEach
//    public void setUpMock() throws EntityNotFoundException
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
//        car2.setBrand("Skoda");
//        car2.setModel("Octavia");
//        car2.setColor("Yellow");
//        car2.setYear(2019);
//
//       carService.saveCar(car1);
//       carService.saveCar(car2);
//
//
////        Voids are usually stubbed with Throwables:
////        doThrow(exception).when(mock).someVoidMethod();
//
////        doReturn(Optional.ofNullable(car1)).when(carRepository).deleteById(1L);
//doThrow(new EntityNotFoundException("Not found")).when(carRepository).deleteById(1L);
//
//        carService = new CarServiceImpl(carRepository);
//
//
//    }
//
//    @Test
//    public void shouldRemoveCarById()
//    {
//
//
//
//        setUpMock();
//
//
//
//        //when
//     carService.removeCarById(1L);
//
//
//        //then
//        assertEquals(2,null);
//
//
//    }
//
//
////    @Mock
////    private CarRepository carRepository;
////
////    @InjectMocks
////    private CarServiceImpl carService;
////
////    @Test
////    public void shouldDeleteCarById()
////    {
////        Car car1 = new Car();
////        car1.setBrand("Skoda");
////        car1.setModel("Octavia");
////        car1.setColor("Yellow");
////        car1.setYear(2019);
////
////        Car car2 = new Car();
////        car2.setBrand("VW");
////        car2.setModel("Golf");
////        car2.setColor("Red");
////        car2.setYear(2022);
////        List<Car> testList =  new ArrayList<>(Arrays.asList(car1,car2));
////
////        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car1));
////
////        carService.removeCarById(car1.getId());
////
//////       verify(carRepository).deleteById(car1.getId());
////
////        verify(carRepository).deleteById(car1.getId());

