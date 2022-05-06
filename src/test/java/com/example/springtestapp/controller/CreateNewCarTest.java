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

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateNewCarTest {

    @Autowired
 public   MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


@Test
        public void shouldCreatNewCar() throws Exception {

            Car car5 = new Car();
            car5.setId(5L);
            car5.setBrand("Fiat");
            car5.setModel("Tipo");
            car5.setColor("White");
            car5.setYear(2019);

            mockMvc.perform(MockMvcRequestBuilders.post("/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(car5)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(5)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Is.is("Fiat")));




        }
    }




