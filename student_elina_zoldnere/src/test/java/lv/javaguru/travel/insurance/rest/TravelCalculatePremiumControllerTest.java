package lv.javaguru.travel.insurance.rest;

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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader reader;

    @Test
    @DisplayName("1.1 personFirstName missing")
    public void controller_ShouldReturnCorrectResponseWhenPersonFirstNameIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.1_Request_personFirstName_missing.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.2 personLastName missing")
    public void controller_ShouldReturnCorrectResponseWhenPersonLastNameIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.2_Request_personLastName_missing.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.3 personFirstName empty")
    public void controller_ShouldReturnCorrectResponseWhenPersonFirstNameIsEmpty() throws Exception {
        String requestFile = "ControllerTest_1.3_Request_personFirstName_empty.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.4 personLastName empty")
    public void controller_ShouldReturnCorrectResponseWhenPersonLastNameIsEmpty() throws Exception {
        String requestFile = "ControllerTest_1.4_Request_personLastName_empty.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.5 personFirstName blank")
    public void controller_ShouldReturnCorrectResponseWhenPersonFirstNameIsBlank() throws Exception {
        String requestFile = "ControllerTest_1.5_Request_personFirstName_blank.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.6 personLastName blank")
    public void controller_ShouldReturnCorrectResponseWhenPersonLastNameIsBlank() throws Exception {
        String requestFile = "ControllerTest_1.6_Request_personLastName_blank.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.7 agreementDateFrom missing")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateFromIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.7_Request_agreementDateFrom_missing.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.8 agreementDateTo missing")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.8_Request_agreementDateTo_missing.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("1.9 all fields missing or empty or blank")
    public void controller_ShouldReturnCorrectResponseWhenAllFieldsMissingOrEmptyOrBlank() throws Exception {
        String requestFile = "ControllerTest_1.9_Request_all_fields_missing_or_empty_or_blank.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(4)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[1].field", is("personLastName")))
                .andExpect(jsonPath("errors[1].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[2].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[2].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[3].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[3].message", is("Must not be empty!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("2.1 agreementDateTo is less than agreementDateFrom")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToLessThanAgreementDateFrom() throws Exception {
        String requestFile = "ControllerTest_2.1_Request_wrong_date_chronology.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must be before agreementDateTo!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("2.2 agreementDateTo is equals agreementDateFrom")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToIsEqualsAgreementDateFrom() throws Exception {
        String requestFile = "ControllerTest_2.2_Request_agreementDateTo_equals_agreementDateFrom.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must be before agreementDateTo!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("2.3 agreementDateFrom is less than current date")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateFromIsLessThanToday() throws Exception {
        String requestFile = "ControllerTest_2.3_Request_agreementDateFrom_less_than_current_date.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be in past!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("2.4 agreementDateTo is less than current date")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToIsLessThanToday() throws Exception {
        String requestFile = "ControllerTest_2.4_Request_agreementDateTo_less_than_current_date.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(2)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must be before agreementDateTo!")))
                .andExpect(jsonPath("errors[1].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[1].message", is("Must not be in past!")))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("3.1 all fields are present and valid")
    public void controller_ShouldReturnCorrectResponseWhenAllFieldsAreValid() throws Exception {
        String requestFile = "ControllerTest_3.1_Request_all_fields_present_and_valid.json";
        mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Jānis")))
                .andExpect(jsonPath("personLastName", is("Bērziņš")))
                .andExpect(jsonPath("agreementDateFrom", is("2025-03-10")))
                .andExpect(jsonPath("agreementDateTo", is("2025-03-11")))
                .andExpect(jsonPath("agreementPrice", is(1)))
                .andReturn();
    }

}
