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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("flyway")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetCarByIdTest {

    @Autowired
    Flyway flyway;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void cleanAndRestoreDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldReturnCarByIdTest() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/cars/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.brand", Is.is("BMW")))
                .andExpect(jsonPath("$.model", Is.is("1")))
                .andExpect(jsonPath("$.color", Is.is("yellow")))
                .andExpect(jsonPath("$.year", Is.is(2020)));



    }


}
