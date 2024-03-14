package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    @Test
    @DisplayName("Test case 1: firstName is not provided")
    public void firstNameNotProvided() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_firstName_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_firstName_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test case 2: lastName is not provided")
    public void lastNameNotProvided() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_lastName_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_lastName_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom is not provided")
    public void agreementDateFromNotProvided() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo is not provided")
    public void agreementDateToNotProvided() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test case 5: all fields is not provided")
    public void allFieldsNotProvided() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_allFields_not_provided.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_allFields_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo < agreementDateFrom")
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

    @Test
    @DisplayName("Test case 7: success")
    public void success() throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequest_success.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_success.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }

}
