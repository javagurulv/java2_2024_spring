package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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

@Disabled
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
        executeAndEvaluate("rest/PremiumControllerRequest_personFirstName_is_null_or_empty.json",
                "rest/PremiumControllerResponse_personFirstName_is_null_or_empty.json");
    }

    @Test
    @DisplayName("2.personLastName is null or empty")
    public void personLastNameIsNullOrEmpty() throws Exception {
        executeAndEvaluate
        ("rest/PremiumControllerRequest_personLastName_is_null_or_empty.json",
        "rest/PremiumControllerResponse_personLastName_is_null_or_empty.json");
    }

    @Test
    @DisplayName("3.agreementDateFrom is null or empty")
    public void agreementDateFromIsNullOrEmpty() throws Exception {
        executeAndEvaluate
        ("rest/PremiumControllerRequest_agreementDateFrom_is_null_or_empty.json",
        "rest/PremiumControllerResponse_agreementDateFrom_is_null_or_empty.json");
    }

    @Test
    @DisplayName("4.agreementDateTo is null or empty")
    public void agreementDateToIsNullOrEmpty() throws Exception {
        executeAndEvaluate
        ("rest/PremiumControllerRequest_agreementDateTo_is_null_or_empty.json",
        "rest/PremiumControllerResponse_agreementDateTo_is_null_or_empty.json");
    }

    @Test
    @DisplayName("5.All fields is null or empty")
    public void allFieldsIsNullOrEmpty() throws Exception {
        executeAndEvaluate
        ("rest/PremiumControllerRequest_all_fields_is_null_or_empty.json",
        "rest/PremiumControllerResponse_all_fields_is_null_or_empty.json");
    }

    @Test
    @DisplayName("6.agreementDateTo less than agreementDateFrom")
    public void agreementDateToLessThanAgreementDateFrom() throws Exception {
        executeAndEvaluate
        ("rest/PremiumControllerRequest_agreementDateTo_less_than_agreementDateFrom.json",
        "rest/PremiumControllerResponse_agreementDateTo_less_than_agreementDateFrom.json");
    }

    @Test
    @DisplayName("7.All fields are specified")
    public void allFieldsAreSpecified() throws Exception {
        executeAndEvaluate
        ("rest/PremiumControllerRequest_all_fields_are_specified.json",
        "rest/PremiumControllerResponse_all_fields_are_specified.json");
    }

    @Test
    @DisplayName("8.agreementDateFrom is in the past")
    public void agreementDateFromIsInThePast() throws Exception {
        executeAndEvaluate("rest/PremiumControllerRequest_agreementDateFrom_is_in_the_past.json",
                "rest/PremiumControllerResponse_agreementDateFrom_is_in_the_past.json");
    }

    @Test
    @DisplayName("9.agreementDateTo_is_in_the_past")
    public void agreementDateToIsInThePast() throws Exception {
        executeAndEvaluate("rest/PremiumControllerRequest_agreementDateTo_is_in_the_past.json",
                "rest/PremiumControllerResponse_agreementDateTo_is_in_the_past.json");
    }

    private void executeAndEvaluate(String jsonRequestFilePath, String jsonResponseFilePath) throws Exception {
        MvcResult calculatedResult = mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile(jsonRequestFilePath))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = calculatedResult.getResponse().getContentAsString();
        String expectedJsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);
        assertEquals(mapper.readTree(responseContent), mapper.readTree(expectedJsonResponse));
    }

}