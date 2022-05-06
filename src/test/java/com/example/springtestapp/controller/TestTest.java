package com.example.springtestapp.controller;

import com.example.springtestapp.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableWebMvc
public class TestTest {

    @Autowired
    public   MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;





    @Test
    public void shouldReturnAllCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andDo(print());
    }

    @Test
    public void shouldReturnCarByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/cars/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andDo(print());
    }


    @Test
    public void shouldCreateCar() throws Exception {
        Car car4 = new Car();
        car4.setId(4L);
        car4.setBrand("Skoda");
        car4.setModel("Octavia");
        car4.setColor("yellow");
        car4.setYear(2019);


            this.mockMvc.perform( MockMvcRequestBuilders.post("/add")
                    .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(car4)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(4)))
                .andExpect(jsonPath("$.brand", Is.is("Skoda")));
    }


    @Test
    public void testowyTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/update/1","Audi", "TT", "Pink",2022 ) ) .andDo(print());
    }

    @Test
    public void shouldUpdateCar() throws Exception {



        this.mockMvc.perform(MockMvcRequestBuilders.put("/update/1/Renulat/Twingo/Black/2029")

                .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model",Is.is("Twingo")));
    }

    @Test
    public void shouldRemoveCar() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/cars/remove/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(IsNull.nullValue()));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars")).andDo(print());


    }

    @Test
    public void shouldReturnCarsWithColot() throws Exception
    {

        

        this.mockMvc.perform(MockMvcRequestBuilders.get("/color/yellow")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color", Is.is("yellow")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Is.is(2))

                );



    }

    }



//    private MockMvc mockMvc;
//    @InjectMocks
//    CarController carController;
//    @Mock
//    CarRepository carRepository;
//
//    @MockBean
//    CarService carService;
//
//    @Before
//    public void setUp(){
//        mockMvc = MockMvcBuilders.standaloneSetup(carController)
//                .build();
//    }
//    @Test
//    public void getAllItems() throws Exception {
//        List<Car> items = new ArrayList<>();
//        Car car = new Car();
//        car.setId(1L);
//        car.setBrand("Skoda");
//        car.setModel("Octavia");
//        car.setColor("Yellow");
//        car.setYear(2019);
//        items.add(car);
//
//
//        Mockito.when(carRepository.findAll()).thenReturn(items);
//        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }

//    @Mock
//    private MockMvc mockMvc;
//    @InjectMocks
//   CarController carController;
//    @Mock
//    CarRepository carRepository;
//
//    @Before
//    public void setUp(){
//        mockMvc = MockMvcBuilders.standaloneSetup(carController)
//                .build();
//    }
//
//
//@Test
//    public void getItem() throws Exception{
//        Car car = new Car();
//        car.setId(1L);
//        car.setBrand("Skoda");
//        car.setModel("Octavia");
//        car.setColor("Yellow");
//        car.setYear(2019);
//        Mockito.when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));
//        mockMvc.perform(MockMvcRequestBuilders.get("/cars/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
//
//        Mockito.verify(carRepository).getById(1L);
//    }


