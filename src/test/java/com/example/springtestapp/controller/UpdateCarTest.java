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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ActiveProfiles("flyway")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateCarTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldUpdateCar() throws Exception {


        Car carUpdated = new Car();
        carUpdated.setBrand("Fiat");
        carUpdated.setModel("Tipo");
        carUpdated.setColor("White");
        carUpdated.setYear(2019);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(carUpdated)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Is.is("Fiat")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Is.is("Tipo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Is.is("White")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year", Is.is(2019)))
                .andDo(print());

    }

}
