package lv.javaguru.travel.insurance.rest;

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

import java.awt.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("1.personFirstName is null or empty")
    public void personFirstNameIsNullOrEmpty() throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/PremiumControllerRequest_personFirstName_is_null_or_empty.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile("rest/PremiumControllerResponse_personFirstName_is_null_or_empty.json");
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));


    }

    @Test
    @DisplayName("2.personLastName is null or empty")
    public void personLastNameIsNullOrEmpty() throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/PremiumControllerRequest_personLastName_is_null_or_empty.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile("rest/PremiumControllerResponse_personLastName_is_null_or_empty.json");
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));

    }

    @Test
    @DisplayName("3.agreementDateFrom is null or empty")
    public void agreementDateFromIsNullOrEmpty() throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/PremiumControllerRequest_agreementDateFrom_is_null_or_empty.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile("rest/PremiumControllerResponse_agreementDateFrom_is_null_or_empty.json");
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));
    }

    @Test
    @DisplayName("4.agreementDateTo is null or empty")
    public void agreementDateToIsNullOrEmpty() throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/PremiumControllerRequest_agreementDateTo_is_null_or_empty.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile("rest/PremiumControllerResponse_agreementDateTo_is_null_or_empty.json");
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));
    }

    @Test
    @DisplayName("5.All fields is null or empty")
    public void allFieldsIsNullOrEmpty() throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/PremiumControllerRequest_all_fields_is_null_or_empty.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile("rest/PremiumControllerResponse_all_fields_is_null_or_empty.json");
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));
    }

    @Test
    @DisplayName("6.agreementDateTo less than agreementDateFrom")
    public void agreementDateToLessThanAgreementDateFrom() throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/PremiumControllerRequest_agreementDateTo_less_than_agreementDateFrom.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile("rest/PremiumControllerResponse_agreementDateTo_less_than_agreementDateFrom.json");
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));
    }

    @Test
    @DisplayName("7.All fields are specified")
    public void allFieldsAreSpecified() throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/PremiumControllerRequest_all_fields_are_specified.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile("rest/PremiumControllerResponse_all_fields_are_specified.json");
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));
    }







}