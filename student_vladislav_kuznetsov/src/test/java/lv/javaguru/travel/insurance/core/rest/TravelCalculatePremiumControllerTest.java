package lv.javaguru.travel.insurance.core.rest;
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

import java.sql.SQLException;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    @Test
    @Disabled
    @DisplayName("Test case 1: personFirstName is not entered")
    public void personFirstNameIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_personFirstName_is_not_entered.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_personFirstName_is_not_entered.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 2: personLastName is not entered")
    public void personLastNameIsNotEntered() throws Exception {
        String jsonRequest= "rest/TravelCalculatePremiumRequest_personLastName_is_not_entered.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_personLastName_is_not_entered.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 3: personFirstName and personLastName is not entered")
    public void personFirstNameAndPersonLastNameIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_personFirstName_and_personLastName_is_not_entered.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_personFirstName_and_personLastName_is_not_entered.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 4: agreementDateFromIsNotEntered")
    public void agreementDateFromIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateFrom_is_not_entered.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateFrom_is_not_entered.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 5: agreementDateToIsNotEntered")
    public void agreementDateToIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_not_entered.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateTo_is_not_entered.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 6: agreementDateTo and AgreementDateFrom IsNotEntered")
    public void agreementDateToAndAgreementDateFromIsNotEntered() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_and_agreementDateFrom_is_not_entered.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateFrom_and_agreementDateTo_is_not_entered.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 7: agreementDateTo is before agreementDateFrom")
    public void agreementDateToIsBeforeAgreementDateFrom() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_before_agreementDateFrom.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateTo_is_before_agreementDateFrom.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 8: agreementDateTo is equal agreementDateFrom")
    public void agreementDateToIsEqualAgreementDateFrom() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateFrom_is_equal_agreementDateTo.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateTo_is_equal_agreementDateFrom.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 9: agreementDateFrom is before currentDate")
    public void agreementDateFromIsBeforeCurrentDate() throws Exception {
        String jsonRequest = "rest/TravelCalclatePremiumRequest_agreementDateFrom_is_before_currentDate.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateFrom_is_before_currentDate.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 10: personFirstName, personLastName, agreementDateFrom, agreementDateTo : is entered")
    public void allFieldsAreCorrect() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_all_fields_are_correct.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_all_fields_are_entered_and_are_valid.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 11: agreementDateFrom, agreementDateTo entered in wrong Date format")
    public void agreementDateFromAndAgreementDateToIsWrongDateFormat() throws Exception {
        String jsonRequest = "rest/TravelCalclatePremiumRequest_agreementDateFrom_agreementDateTo_is_wrong_date_format.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateFrom_and_agreementDateTo_is_wrong_format.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 12: personFirstName, personLastName, agreementDateFrom, agreementDateTo : is not entered.")
    public void allFieldsAreNull() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_all_fields_are_null.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_all_fields_are_empty.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 13: PersonFirstName, personLastName, agreementDateFrom is entered. agreementDateTo is entered in wrong Date format")
    public void agreementDateToIsWrongDateFormat() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_wrong_date_format.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_only_agreementDateTo_is_wrong_date_format.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 14: agreementDateFrom and agreementDateTo is before current date and equal")
    public void agreementDateToAndAgreementDateFromIsBeforeCurrentTimeAndEqual() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateFrom_agreementDateTo_is_before_current_date_and_equal.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateFrom_and_agreementDateTo_is_in_the_past.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 15: agreementDateTo is before current date")
    public void agreementDateToIsBeforeCurrentDate() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_before_current_date.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_when_agreementDateTo_is_before_current_time.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 16: agreementDateFrom and agreementDateTo is before current date and agreementDateFrom is before agreementDateTo")
    public void agreementDateFromAndAgreementDateToIsBeforeCurrentDateAndAgreementDateFromIsBeforeAgreementDateTo() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_agreementDateFrom_and_agreementDateTo_is_before_current_time_and_agreementDateFrom_is_before_agreementDateTo.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_agreementDateFrom_and_agreementDateTo_is_before_current_date_and_agreementDateFrom_is_before_agreementDateto.json";
        executeAndCompare(jsonRequest, jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 17: Selected risks are empty")
    public void selectedRisksAreEmpty() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_Selected_risks_are_empty.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_Selected_risks_are_empty.json";
        executeAndCompare(jsonRequest,jsonResponse);
    }

    @Test
    @Disabled
    @DisplayName("Test case 18: Selected risks are null")
    public void selectedRisksAreNull() throws Exception {
        String jsonRequest = "rest/TravelCalculatePremiumRequest_selected_risks_are_null.json";
        String jsonResponse = "rest/TravelCalculatePremiumResponse_selected_risks_are_null.json";
        executeAndCompare(jsonRequest, jsonResponse);
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
        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonResponse);
    }
}
