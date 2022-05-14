package com.example.springtestapp.controller;

import org.flywaydb.core.Flyway;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("flyway")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetAllCarsTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    Flyway flyway;

    @BeforeEach
    void cleanAndRestoreDatabase() {
        flyway.clean();
        flyway.migrate();
    }


    @Test
    public void shouldReturnAllCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[0].brand", Is.is("BMW")))
                .andExpect(jsonPath("$[1].brand", Is.is("Seat")))
                .andExpect(jsonPath("$[2].color", Is.is("white")))
                .andExpect(jsonPath("$[3].year", Is.is(2021)))
                .andDo(print());
    }


}
