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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    @DisplayName("Test 1: no errors")
    public void premiumControllerTestNoErrors() throws Exception {
        generalizingAndComparing("rest/TravelCalculatePremiumRequest_noErrors.json",
                "rest/TravelCalculatePremiumResponse_noErrors.json");

    }

    @Test
    @DisplayName("Test 2: first name field is empty")
    public void firstNameFieldIsEmpty() throws Exception {
        generalizingAndComparing("rest/TravelCalculatePremiumRequest_firstName_empty.json",
                "rest/TravelCalculatePremiumResponse_firstName_empty.json");
    }

    @Test
    @DisplayName("Test 3: last name field is empty")
    public void lastNameFieldIsEmpty() throws Exception {
        generalizingAndComparing("rest/TravelCalculatePremiumRequest_lastName_empty.json",
                "rest/TravelCalculatePremiumResponse_lastName_empty.json");
    }

    @Test
    @DisplayName("Test 4: date from field is empty")
    public void dateFromFieldIsEmpty() throws Exception {
        generalizingAndComparing("rest/TravelCalculatePremiumRequest_dateFrom_empty.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_empty.json");
    }

    @Test
    @DisplayName("Test 5: date to field is empty")
    public void dateToFieldIsEmpty() throws Exception {
        generalizingAndComparing("rest/TravelCalculatePremiumRequest_dateTo_empty.json",
                "rest/TravelCalculatePremiumResponse_dateTo_empty.json");
    }

    @Test
    @DisplayName("Test 6: date to is equal to date from")
    public void dateToIsEqualToDateFrom() throws Exception {
        generalizingAndComparing("rest/TravelCalculatePremiumRequest_dateFrom_equals_dateTo.json",
                "rest/TravelCalculatePremiumResponse_dateFrom_equals_dateTo.json");
    }

    @Test
    @DisplayName("Test 7: date to is earlier than date from")
    public void dateToIsEarlierThanDateFrom() throws Exception {
        generalizingAndComparing("rest/TravelCalculatePremiumRequest_dateTo_earlier_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_earlier_dateFrom.json");
    }

    private void generalizingAndComparing(String jsonRequestFilePath, String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.getJsonFromFile(jsonRequestFilePath);

        MvcResult mvcResult = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseOtherValue = mvcResult.getResponse().getContentAsString();
        String responseJsonValue = jsonFileReader.getJsonFromFile(jsonResponseFilePath);
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.readTree(responseOtherValue), objectMapper.readTree(responseJsonValue));
    }

}

