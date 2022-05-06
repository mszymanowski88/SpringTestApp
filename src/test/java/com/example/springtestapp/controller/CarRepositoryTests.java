package com.example.springtestapp.controller;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
//@SpringBootTest
public class CarRepositoryTests {
//
//    @MockBean
//    CarService carService;
//    @MockBean
//    CarRepository carRepository;
//
//
//    @Autowired
//    public CarRepositoryTests(CarRepository carRepository,CarService carService) {
//        this.carRepository = carRepository;
//     this.carService = carService;
//    }
//
//
//
//
//
//    @Test
//    public void ShouldReturnCar()
//    {
//        Car car1 = new Car(1L,"Skoda","Octavia","Yellow",2019);
//        Car car2 = new Car(2L,"VW","Golf","White",2022);
//        List<Car> records =  new ArrayList<>(Arrays.asList(car1,car2));
//        carRepository.save(car1);
//        carRepository.save(car2);
//
//        Car testCar = carRepository.findById(1L).get();
//
//        Assertions.assertThat(testCar.getId()).isEqualTo(1L);
//
//    }
//
//
//    @Test
//    public void ShouldDeletCarById()
//    {
//        //given
//        Car car1 = new Car(1L,"Skoda","Octavia","Yellow",2019);
//        Car car2 = new Car(2L,"VW","Golf","White",2022);
//        List<Car> records =  new ArrayList<>(Arrays.asList(car1,car2));
//
//        //when
//        List<Car> testList = Arrays.asList(car1,car2);
//
//        //then
//        Assertions.assertThat(records.size()).isEqualTo(2);
//
//    }
}
