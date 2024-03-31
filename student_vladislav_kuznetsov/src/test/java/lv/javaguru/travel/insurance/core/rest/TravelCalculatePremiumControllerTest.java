package lv.javaguru.travel.insurance.core.rest;

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

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;
    @Autowired private JsonFileComparator jsonFileComparator;

    @Test
    @DisplayName("Test case 1: personFirstName is not entered")
    public void personFirstNameIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_personFirstName_is_not_entered.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_personFirstName_is_not_entered.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 2: personLastName is not entered")
    public void personLastNameIsNotEntered() throws Exception {
        String jsonRequest= "rest/TravelCalculatePremiumRequest_personLastName_is_not_entered.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_personLastName_is_not_entered.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 3: personFirstName and personLastName is not entered")
    public void personFirstNameAndPersonLastNameIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_personFirstName_and_personLastName_is_not_entered.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_personFirstName_and_personLastName_is_not_entered.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 4: agreementDateFromIsNotEntered")
    public void agreementDateFromIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateFrom_is_not_entered.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_agreementDateFrom_is_not_entered.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 5: agreementDateToIsNotEntered")
    public void agreementDateToIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_not_entered.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_agreementDateTo_is_not_entered.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo and AgreementDateFrom IsNotEntered")
    public void agreementDateToAndAgreementDateFromIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_and_agreementDateFrom_is_not_entered.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_agreementDateFrom_and_agreementDateTo_is_not_entered.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 7: agreementDateTo is before agreementDateFrom")
    public void agreementDateToIsBeforeAgreementDateFrom() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_before_agreementDateFrom.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_agreementDateTo_is_before_agreementDateFrom.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 8: agreementDateTo is equal agreementDateFrom")
    public void agreementDateToIsEqualAgreementDateFrom() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateFrom_is_equal_agreementDateTo.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_agreementDateTo_is_equal_agreementDateFrom.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 9: agreementDateFrom is before currentDate")
    public void agreementDateFromIsBeforeCurrentDate() throws Exception {
        String jsonRequest = "rest/TravelCalclatePremiumRequest_agreementDateFrom_is_before_currentDate.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_agreementDateFrom_is_before_currentDate.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 10: personFirstName, personLastName, agreementDateFrom, agreementDateTo : is entered")
    public void allFieldsAreCorrect() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_all_fields_are_correct.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_all_fields_are_entered_and_are_valid.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 11: agreementDateFrom, agreementDateTo entered in wrong Date format")
    public void agreementDateFromAndAgreementDateToIsWrongDateFormat() throws Exception {
        String jsonRequest = "rest/TravelCalclatePremiumRequest_agreementDateFrom_agreementDateTo_is_wrong_date_format.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_agreementDateFrom_and_agreementDateTo_is_wrong_format.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 12: personFirstName, personLastName, agreementDateFrom, agreementDateTo : is not entered.")
    public void allFieldsAreNull() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_all_fields_are_null.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_all_fields_are_empty.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }

    @Test
    @DisplayName("Test case 13: PersonFirstName, personLastName, agreementDateFrom is entered. agreementDateTo is entered in wrong Date format")
    public void agreementDateToIsWrongDateFormat() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_wrong_date_format.json";
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(jsonRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumResponse_when_only_agreementDateTo_is_wrong_date_format.json");
        assertTrue(jsonFileComparator.compareJsonFile(responseBodyContent,jsonResponse));
    }
}
