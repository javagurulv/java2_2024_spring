package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void shouldReturnWithOutErrors() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithoutErrors.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponseWithoutErrors.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    public void shouldReturnWithIncorrectDateError() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithIncorrectDate.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponseWithIncorrectDate.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    public void shouldReturnWithNullFirstNameError() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithFirstNameNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponseWithFirstNameNull.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    public void shouldReturnWithNullLastNameError() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithLastNameNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponseWithLastNameNull.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    public void shouldReturnWithNullDateFromError() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithDateFromNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponseWithDateFromNull.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    public void shouldReturnWithNullDateToError() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithDateToNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponseWithDateToNull.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    public void shouldReturnWithAllNullErrors() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithAllFieldsNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponseWithAllFieldsNull.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

}