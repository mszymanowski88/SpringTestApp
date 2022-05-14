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

@ActiveProfiles("flyway")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeColorTest {

    @Autowired
    CarController controller;
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    Flyway flyway;

    @BeforeEach
    void cleanAndRestoreDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldUpdateCar() throws Exception {


        this.mockMvc.perform(MockMvcRequestBuilders.put("/colorupdate/1")
                .contentType(MediaType.APPLICATION_JSON)

                .content("pink"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Is.is("pink")));
        mockMvc.perform(MockMvcRequestBuilders.get("/cars")).andDo(print());


    }

}
