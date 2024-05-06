package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void simpleRestControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content("{" +
                        "\"personFirstName\" : \"Tom\",\n" +
                        "\"personLastName\" : \"Sawyer\",\n" +
                        "\"agreementDateFrom\" : \"2005-05-15\",\n" +
                        "\"agreementDateTo\" : \"2005-05-20\"\n" +
                        "}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Tom")))
                .andExpect(jsonPath("personLastName", is("Sawyer")))
                .andExpect(jsonPath("agreementDateFrom", is("2005-05-15")))
                .andExpect(jsonPath("agreementDateTo", is("2005-05-20")))
                .andExpect(jsonPath("agreementPrice", is(5)))
                .andReturn();

    }



}