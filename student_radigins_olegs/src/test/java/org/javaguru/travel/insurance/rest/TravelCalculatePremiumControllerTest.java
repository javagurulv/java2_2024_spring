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
        executeAndCompare("rest/TravelCalculatePremiumRequestWithoutErrors.json",
                "rest/TravelCalculatePremiumResponseWithoutErrors.json");

    }

    @Test
    public void shouldReturnWithIncorrectDateError() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequestWithIncorrectDate.json",
                "rest/TravelCalculatePremiumResponseWithIncorrectDate.json");

    }

    @Test
    public void shouldReturnWithNullFirstNameError() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequestWithFirstNameNull.json",
                "rest/TravelCalculatePremiumResponseWithFirstNameNull.json");

    }

    @Test
    public void shouldReturnWithNullLastNameError() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequestWithLastNameNull.json",
                "rest/TravelCalculatePremiumResponseWithLastNameNull.json");

    }

    @Test
    public void shouldReturnWithNullDateFromError() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequestWithDateFromNull.json",
                "rest/TravelCalculatePremiumResponseWithDateFromNull.json");

    }

    @Test
    public void shouldReturnWithNullDateToError() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequestWithDateToNull.json",
                "rest/TravelCalculatePremiumResponseWithDateToNull.json" );

    }

    @Test
    public void shouldReturnWithAllNullErrors() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequestWithAllFieldsNull.json",
                "rest/TravelCalculatePremiumResponseWithAllFieldsNull.json");

    }


    private void executeAndCompare(String jsonRequestFilePath,
                                   String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

}