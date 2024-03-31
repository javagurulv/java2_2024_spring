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

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    @Test
    @DisplayName("Test case 1: personFirstName is not entered")
    public void personFirstNameIsNotEntered() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_personFirstName_is_not_entered.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 2: personLastName is not entered")
    public void personLastNameIsNotEntered() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_personLastName_is_not_entered.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 3: personFirstName and personLastName is not entered")
    public void personFirstNameAndPersonLastNameIsNotEntered() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_personFirstName_and_personLastName_is_not_entered.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(2)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[1].field", is("personLastName")))
                .andExpect(jsonPath("errors[1].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 4: agreementDateFromIsNotEntered")
    public void agreementDateFromIsNotEntered() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_agreementDateFrom_is_not_entered.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 5: agreementDateToIsNotEntered")
    public void agreementDateToIsNotEntered() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_not_entered.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo and AgreementDateFrom IsNotEntered")
    public void agreementDateToAndAgreementDateFromIsNotEntered() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_agreementDateTo_and_agreementDateFrom_is_not_entered.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(2)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[1].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[1].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 7: agreementDateTo is before agreementDateFrom")
    public void agreementDateToIsBeforeAgreementDateFrom() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_before_agreementDateFrom.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Invalid date !")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 8: agreementDateTo is equal agreementDateFrom")
    public void agreementDateToIsEqualAgreementDateFrom() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_agreementDateFrom_is_equal_agreementDateTo.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Invalid date !")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 9: agreementDateFrom is before currentDate")
    public void agreementDateFromIsBeforeCurrentDate() throws Exception {
        String filePath = "rest/TravelCalclatePremiumRequest_agreementDateFrom_is_before_currentDate.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Invalid date !")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 10: personFirstName, personLastName, agreementDateFrom, agreementDateTo : is entered")
    public void allFieldsAreCorrect() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_all_fields_are_correct.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Vasja")))
                .andExpect(jsonPath("personLastName", is("Pupkin")))
                .andExpect(jsonPath("agreementDateFrom", is("2024-05-29")))
                .andExpect(jsonPath("agreementDateTo", is("2024-05-30")))
                .andExpect(jsonPath("agreementPrice", is(1)))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 11: agreementDateFrom, agreementDateTo entered in wrong Date format")
    public void agreementDateFromAndAgreementDateToIsWrongDateFormat() throws Exception {
        String filePath = "rest/TravelCalclatePremiumRequest_agreementDateFrom_agreementDateTo_is_wrong_date_format.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Invalid date !")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 12: personFirstName, personLastName, agreementDateFrom, agreementDateTo : is not entered.")
    public void allFieldsAreNull() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_all_fields_are_null.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(4)))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 13: PersonFirstName, personLastName, agreementDateFrom is entered. agreementDateTo is entered in wrong Date format")
    public void agreementDateToIsWrongDateFormat() throws Exception {
        String filePath = "rest/TravelCalculatePremiumRequest_agreementDateTo_is_wrong_date_format.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Invalid date !")))
                .andReturn();
    }
}
