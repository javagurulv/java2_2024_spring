package org.javaguru.travel.insurance.rest;

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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void shouldReturnWithOutErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithoutErrors.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Olegs")))
                .andExpect(jsonPath("personLastName", is("Radigins")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(4)))
                .andReturn();
    }

    @Test
    public void shouldReturnWithIncorrectDateError() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithIncorrectDate.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreement date to")))
                .andExpect(jsonPath("errors[0].message", is("Must be after!")))
                .andReturn();
    }

    @Test
    public void shouldReturnWithNullFirstNameError() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithFirstNameNull.json"))
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
    public void shouldReturnWithNullLastNameError() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithLastNameNull.json"))
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
    public void shouldReturnWithNullDateFromError() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithDateFromNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    public void shouldReturnWithNullDateToError() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithDateToNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    public void shouldReturnWithAllNullErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithAllFieldsNull.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(4)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[1].field", is("personLastName")))
                .andExpect(jsonPath("errors[1].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[2].field", is("personDateFrom")))
                .andExpect(jsonPath("errors[2].message", is("Must not be empty!")))
                .andExpect(jsonPath("errors[3].field", is("personDateTo")))
                .andExpect(jsonPath("errors[3].message", is("Must not be empty!")))
                .andReturn();
    }

}