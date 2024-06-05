package lv.javaguru.travel.insurance.rest;

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
    public void firstNameNotEmpty() throws Exception {
        executeAndCompare(
                "rest/PremiumControllerRequest_personFirstName_is_not_empty.json",
                "rest/TravelCalculatePremiumResponse_firstName_not_empty.json"
        );
    }

    @Test
    public void lastNameNotEmpty() throws Exception {
        executeAndCompare(
                "rest/PremiumControllerRequest_personLastName_not_or_empty.json",
                "rest/TravelCalculatePremiumResponse_lastName_not_empty.json"
        );
    }

    @Test
    public void agreementDateFromNotEmpty() throws Exception {
        executeAndCompare(
                "rest/PremiumControllerRequest_agreementDateFrom_null_or_empty.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_not_empty.json"
        );
    }

    @Test
    public void agreementDateToNotEmpty() throws Exception {
        executeAndCompare(
                "rest/PremiumControllerRequest_agreementDateTo_null_or_empty.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_not_empty.json"
        );
    }

    @Test
    public void agreementDateToAndAgreementDateFrom() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_dateTo_and_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_and_dateFrom.json"
        );
    }

    @Test
    public void allFieldsNotEmpty() throws Exception {
        executeAndCompare(
                "rest/PremiumControllerRequest_all_fields_is_not_empty.json",
                "rest/TravelCalculatePremiumResponse_allFields_not_empty.json"
        );
    }

    @Test
    public void RightRequest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest.Right.json",
                "rest/TravelCalculatePremiumResponse_Right.json"
        );
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
