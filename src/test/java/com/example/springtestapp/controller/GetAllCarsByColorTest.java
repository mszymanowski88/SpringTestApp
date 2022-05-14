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
@SpringBootTest
@RunWith(SpringRunner.class)
public class GetAllCarsByColorTest {

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
    public void shouldReturnAllCarsBySelectedColor() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/color/yellow"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("$[0].brand", Is.is("BMW")));


    }
}
