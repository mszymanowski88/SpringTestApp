package com.example.springtestapp.controller;

import com.example.springtestapp.model.Car;
import com.example.springtestapp.repository.CarRepository;
import com.example.springtestapp.service.CarServiceImpl;
import com.example.springtestapp.service.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest()
//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {


    @Autowired
    private MockMvc mockMvc;



@MockBean
CarRepository carRepository;


    @MockBean
    private CarServiceImpl carService;


        @Autowired
        ObjectMapper mapper;





    @Autowired
    Flyway flyway;

    @AfterEach
    void cleanDataBase() {
        flyway.clean();
        flyway.migrate();
    }


//
//    @Before
//    public void setUp(){
//        Car car = new Car();
//        car.setId(1L);
//        car.setBrand("Skoda");
//        car.setModel("Octavia");
//        car.setColor("Yellow");
//        car.setYear(2019);
//        when(carService.saveCar(car)).thenReturn(car);
//        when(carService.saveCar(car)).thenReturn(car);
//    }

    @Test
    public void shouldReturnAllCars() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setBrand("Skoda");
        car.setModel("Octavia");
        car.setColor("Yellow");
        car.setYear(2019);

        Car car1 = new Car();
        car1.setId(2L);
        car1.setBrand("Skoda");
        car1.setModel("Octavia");
        car1.setColor("Yellow");
        car1.setYear(2019);

        List<Car> testList = Arrays.asList(car,car1);

        when(carService.getAllCars()).thenReturn(testList);


//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get("/cars"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$[0].id", Is.is(1)))
//                .andExpect(jsonPath("$.size()", Matchers.is(2)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/cars/{id}",1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Is.is(1)))
//                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$.size()", Matchers.is(2)));

//        this.mockMvc
//                .perform( MockMvcRequestBuilders.get("/cars/1"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$[0].brand").value(1));



    }


    @Test
    public void getEmployeeByIdAPI() throws Exception
    {
        Car car = new Car();
        car.setId(1L);
        car.setBrand("Skoda");
        car.setModel("Octavia");
        car.setColor("Yellow");
        car.setYear(2019);

        Car car1 = new Car();
        car1.setId(2L);
        car1.setBrand("Skoda");
        car1.setModel("Octavia");
        car1.setColor("Yellow");
        car1.setYear(2019);

        List<Car> testList = Arrays.asList(car,car1);

        when(carService.getAllCars()).thenReturn(testList);

       this.mockMvc.perform( MockMvcRequestBuilders
                .get("/cars/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }



    @Test
    public void shouldDeleteCar() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setBrand("Skoda");
        car.setModel("Octavia");
        car.setColor("Yellow");
        car.setYear(2019);

        Car car1 = new Car();
        car1.setId(2L);
        car1.setBrand("Skoda");
        car1.setModel("Octavia");
        car1.setColor("Yellow");
        car1.setYear(2019);

        Mockito.when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/cars/remove/2"))
//                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[2].id", Is.is(null)));
//                .andExpect(jsonPath("$.size()", Matchers.is(2)));
    }

//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/cars/remove/2")
//                .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isNoContent())
//                .andExpect(jsonPath("$.id[2]").isEmpty());
//                .andExpect(result -> assertNull(carRepository.findById(2L)));
//                .andExpect(result -> assertNull(carRepository.findById(2L)));
//                .andExpect(result -> assert;
//                .andExpect(result -> assertEquals(null,car1.getId()));

//        List<Car> testList = Arrays.asList(car,car1);
//
//        doNothing().when(carService).removeCarById(car.getId());
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/remove/1")
//.contentType(MediaType.APPLICATION_JSON))
//.andExpect(status().isNoContent());

    @Test
    public void createRecord_success() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setBrand("Skoda");
        car.setModel("Octavia");
        car.setColor("Yellow");
        car.setYear(2019);

        Mockito.when(carRepository.save(car)).thenReturn(car);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(car));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[1].id", Is.is(1L)));
    }


    @Test(expected = EntityNotFoundException.class)
    public void deletePatientById_notFound() throws Exception {
        Mockito.when(carRepository.findById(2L)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/cars/remove/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
//                .andExpect(result ->
//                        assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
//                .andExpect(result ->
//                        assertEquals("Patient with ID 5 does not exist.", result.getResolvedException().getMessage()));
    }

//
//        System.out.println(carService.getAllCars());
//        System.out.println(testList);
//
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.delete("/cars/remove/1"))
//                .andExpect(status().isNoContent());

    }



//        Mockito.when(carService.saveCar(car)).thenReturn(car);

//        Mockito.when(carService.saveCar(car)).thenReturn(car);



//        given(carService.saveCar(car)).willReturn(car);
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(       mapper.writeValueAsBytes(car.getBrand())))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.brand", is(car.getBrand())));

//        mockMvc.perform(post("/add")
//                .contentType(MediaType.APPLICATION_JSON))
//                .content(       mapper.writeValueAsBytes(car))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.brand", CoreMatchers.is(car.getBrand())));
//
//        MockHttpServletResponse resposne = mockMvc.perform(
//                post("/add")
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//
//        assertThat(resposne.getStatus()).isEqualTo(HttpStatus.CREATED.value());

//        assertThat(resposne.getContentAsString()).isEqualTo(
//                carService.saveCar(car)
//        );

//        mockMvc.perform(post("/add")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(JsonUtil.toJson(user)))
////                .andExpect(status().isCreated())
////                .andExpect(jsonPath("$.name", is(user.getName())));
//                .contentType("application/json"))
//                .andExpect(status().isCreated())
//           .andExpect(jsonPath("$.id", is(car.getBrand())));

//        mockMvc.perform(MockMvcRequestBuilders.post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(car)))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(car.getBrand())));
//        mockMvc.perform(post("/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                .contentType( mapper.writeValueAsString(car)))
//         .andExpect(status().isCreated())
//                .andExpect(car1.getBrand().equals(1L)).;

//                .andExpect(content().string("1"));

//
//        given(carService.saveCar(car)).willReturn(car);
//
//        MvcResult mvcResult = mockMvc.perform(post("/add")).andExpect(status().isCreated()).andReturn();
//
//        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
//
//        String expectedJsonResult = mapper.writeValueAsString(car);
//
//        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResult);
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.toJson(user)))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(user.getName())));
//
//
//        mockMvc.perform(post("/add")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$[0].brand", is(car.getBrand())));
//
//
//        mockMvc.perform(post("/add")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.brand", is(car.getBrand())));

//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(car));
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.name", is("John Doe")));



////    @Autowired
//    MockMvc mockMvc;
////    @Autowired
//    ObjectMapper mapper;
//
//    @Autowired
//    public CarControllerTest(MockMvc mockMvc, ObjectMapper mapper) {
//        this.mockMvc = mockMvc;
//        this.mapper = mapper;
//    }
//
//    @MockBean
//    CarService carService;
//
//    @MockBean
//    CarController carController;
//
//    Car car1 = new Car(1L,"Skoda","Octavia","Yellow",2019);
//    Car car2 = new Car(2L,"VW","Golf","White",2022);
//    List<Car> records =  new ArrayList<>(Arrays.asList(car1,car2));
//
//
//    @Test
//    void getAllCars() throws Exception {
//
//        Car car1 = new Car(1L,"Skoda","Octavia","Yellow",2019);
//        Car car2 = new Car(2L,"VW","Golf","White",2022);
//        List<Car> records =  new ArrayList<>(Arrays.asList(car1,car2));
//
//        Mockito.when(carController.getAllCars()).thenReturn(records);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/cars")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].brand", is("Skoda")));
//
//
//    }
//
//    @Test
//    public void shouldFindCarById() throws Exception {
//
//        Car car1 = new Car(1L,"Skoda","Octavia","Yellow",2019);
//        Car car2 = new Car(2L,"VW","Golf","White",2022);
//        List<Car> records =  new ArrayList<>(Arrays.asList(car1,car2));
//
//
//        Mockito.when(carController.getCarById(car1.getId())).thenReturn(car2);
//
//  mockMvc.perform(MockMvcRequestBuilders
//  .get("/cars/1")
//  .contentType(MediaType.APPLICATION_JSON))
//          .andExpect(status().isOk())
//          .andExpect(jsonPath("$", notNullValue()))
//          .andExpect(jsonPath("$.model", is("Golf")));
//
//
//    }
//
//    @Test
//    public void shouldDeleteCarById() throws Exception {
//
//        Car car1 = new Car(1L,"Skoda","Octavia","Yellow",2019);
//        Car car2 = new Car(2L,"VW","Golf","White",2022);
//        List<Car> records =  new ArrayList<>(Arrays.asList(car1,car2));
//
////        Mockito.when(carController.getCarById(car2.getId())).thenReturn(car2);
////  Mockito.when(carController.deleteCar(car2.getId()).thenReturn("removed"));
//
//        Mockito.when(carController.deleteCar(car1.getId())).thenReturn("SUCCESS");
//
//        mockMvc.perform(MockMvcRequestBuilders
//        .delete("/cars/remove/{id}","10")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//
//
//
//    }
//    @Test
//    public void deletePatientById_notFound() throws Exception {
//        Mockito.when(carController.getCarById(car2.getId())).thenReturn(car2);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/cars/remove/{id}","5")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(result ->
//                        assertTrue(result.getResolvedException() instanceof ChangeSetPersister.NotFoundException))
//                .andExpect(result ->
//                        assertEquals("Patient with ID 5 does not exist.", result.getResolvedException().getMessage()));
//    }
//
////    @Test
////    public void deletePatientById_notFound1() throws Exception {
////        Car car1 =
////
////        carController.deleteCar(car1.getId());
////
////        assertThat(car1).isNull();
/////


