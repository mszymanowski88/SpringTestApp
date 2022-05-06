package com.example.springtestapp.controller;


import com.example.springtestapp.service.CarServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ActiveProfiles("test")
@SpringBootTest()
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarTest {


    @Autowired
    MockMvc mockMvc;
//    @Autowired
//    Flyway flyway;
    @MockBean
    private CarServiceImpl carService;
//    @AfterEach
//    void cleanDataBase() {
//        flyway.clean();
//        flyway.migrate();
//
//    }


    @Test
    public void getEmployeeByIdAPI() throws Exception
    {
       mockMvc.perform( MockMvcRequestBuilders
                .get("/cars/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].size").value(1));
    }


    @Test
    public void shouldReturnAllCars() throws Exception {
//        Car car = new Car();
//        car.setId(1L);
//        car.setBrand("Skoda");
//        car.setModel("Octavia");
//        car.setColor("Yellow");
//        car.setYear(2019);
//
//        Car car1 = new Car();
//        car1.setId(2L);
//        car1.setBrand("Skoda");
//        car1.setModel("Octavia");
//        car1.setColor("Yellow");
//        car1.setYear(2019);
//
//        List<Car> testList = Arrays.asList(car,car1);
//
//        when(carService.getAllCars()).thenReturn(testList);



        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(2)));
    }
}
