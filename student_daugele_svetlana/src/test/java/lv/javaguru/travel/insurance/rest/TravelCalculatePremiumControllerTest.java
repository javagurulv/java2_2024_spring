package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc


class TravelCalculatePremiumControllerTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;
/*
    @Test
    public void successRequest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json"
        );
    }

    @Test
    public void firstNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_firstName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_firstName_not_provided.json"
        );
    }

    @Test
    public void lastNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_lastName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_lastName_not_provided.json"
        );
    }
/*
    @Test
    public void agreementDateFromNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json"
        );
    }
*/
    /*@Test
    public void agreementDateToNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json"
        );
    }

    @Test
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json"
        );
    }
/*
    @Test
    public void allFieldsNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_allFields_not_provided.json",
                "rest/TravelCalculatePremiumResponse_allFields_not_provided.json"
        );
    }
*/
    /*private void executeAndCompare(String jsonRequestFilePath,
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
*/
    /*
private void executeAndCompare(String jsonRequestFilePath, String jsonResponseFilePath) throws Exception {
    // Load JSON request content from file
    String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

    // Perform POST request with JSON content
    MvcResult result = mockMvc.perform(post("/insurance/travel/")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    // Extract response body content
    String responseBodyContent = result.getResponse().getContentAsString();

    // Load expected JSON response content from file
    String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

    // Convert response content strings to JSON objects
    ObjectMapper mapper = new ObjectMapper();
    JsonNode expectedJson = mapper.readTree(jsonResponse);
    JsonNode actualJson = mapper.readTree(responseBodyContent);

    // Compare expected and actual JSON responses
    assertEquals(expectedJson, actualJson);
}
*/
}