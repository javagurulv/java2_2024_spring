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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1: no errors")
    public void premiumControllerTestNoErrors() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(
                            "{" +
                            "\"personFirstName\" : \"Bob\",\n" +
                            "\"personLastName\" : \"Johnson\",\n" +
                            "\"agreementDateFrom\" : \"2010-10-10\",\n" +
                            "\"agreementDateTo\" : \"2010-10-11\"\n" +
                            "}"
                            )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Bob")))
                .andExpect(jsonPath("personLastName", is("Johnson")))
                .andExpect(jsonPath("agreementDateFrom", is("2010-10-10")))
                .andExpect(jsonPath("agreementDateTo", is("2010-10-11")))
                .andExpect(jsonPath("agreementPrice", is(1)))
                .andExpect(jsonPath("validationErrors", is(nullValue())))
                .andReturn();
    }

    @Test
    @DisplayName("Test 2: first name field is empty")
    public void firstNameFieldIsEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(
                                "{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : \"Johnson\",\n" +
                                "\"agreementDateFrom\" : \"2010-10-10\",\n" +
                                "\"agreementDateTo\" : \"2010-10-11\"\n" +
                                "}"
                                )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("validationErrors", is(notNullValue())))
                .andExpect(jsonPath("validationErrors", hasSize(1)))
                .andExpect(jsonPath("validationErrors[0].field", is("firstName")))
                .andExpect(jsonPath("validationErrors[0].message", is("Field cannot be empty")))
                .andReturn();
    }

    @Test
    @DisplayName("Test 3: last name field is empty")
    public void lastNameFieldIsEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(
                                "{" +
                                "\"personFirstName\" : \"Bob\",\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : \"2010-10-10\",\n" +
                                "\"agreementDateTo\" : \"2010-10-11\"\n" +
                                "}"
                                )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("validationErrors", is(notNullValue())))
                .andExpect(jsonPath("validationErrors", hasSize(1)))
                .andExpect(jsonPath("validationErrors[0].field", is("lastName")))
                .andExpect(jsonPath("validationErrors[0].message", is("Field cannot be empty")))
                .andReturn();
    }

    @Test
    @DisplayName("Test 4: date from field is empty")
    public void dateFromFieldIsEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(
                                "{" +
                                "\"personFirstName\" : \"Bob\",\n" +
                                "\"personLastName\" : \"Johnson\",\n" +
                                "\"agreementDateFrom\" : null,\n" +
                                "\"agreementDateTo\" : \"2010-10-11\"\n" +
                                "}"
                                )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("validationErrors", is(notNullValue())))
                .andExpect(jsonPath("validationErrors", hasSize(1)))
                .andExpect(jsonPath("validationErrors[0].field", is("dateFrom")))
                .andExpect(jsonPath("validationErrors[0].message", is("Field cannot be empty")))
                .andReturn();
    }

    @Test
    @DisplayName("Test 5: date to field is empty")
    public void dateToFieldIsEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(
                                "{" +
                                "\"personFirstName\" : \"Bob\",\n" +
                                "\"personLastName\" : \"Johnson\",\n" +
                                "\"agreementDateFrom\" : \"2010-10-10\",\n" +
                                "\"agreementDateTo\" : null\n" +
                                "}"
                        )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("validationErrors", is(notNullValue())))
                .andExpect(jsonPath("validationErrors", hasSize(1)))
                .andExpect(jsonPath("validationErrors[0].field", is("dateTo")))
                .andExpect(jsonPath("validationErrors[0].message", is("Field cannot be empty")))
                .andReturn();
    }

    @Test
    @DisplayName("Test 6: date to is equal to date from")
    public void dateToIsEqualToDateFrom() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(
                                "{" +
                                "\"personFirstName\" : \"Bob\",\n" +
                                "\"personLastName\" : \"Johnson\",\n" +
                                "\"agreementDateFrom\" : \"2010-10-10\",\n" +
                                "\"agreementDateTo\" : \"2010-10-10\"\n" +
                                "}"
                                )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("validationErrors", is(notNullValue())))
                .andExpect(jsonPath("validationErrors", hasSize(1)))
                .andExpect(jsonPath("validationErrors[0].field", is("dateTo")))
                .andExpect(jsonPath("validationErrors[0].message", is("Cannot be smaller than dateFrom")))
                .andReturn();
    }

    @Test
    @DisplayName("Test 7: date to is earlier than date from")
    public void dateToIsEarlierThanDateFrom() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content(
                                "{" +
                                "\"personFirstName\" : \"Bob\",\n" +
                                "\"personLastName\" : \"Johnson\",\n" +
                                "\"agreementDateFrom\" : \"2010-10-11\",\n" +
                                "\"agreementDateTo\" : \"2010-10-10\"\n" +
                                "}"
                                )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("validationErrors", is(notNullValue())))
                .andExpect(jsonPath("validationErrors", hasSize(1)))
                .andExpect(jsonPath("validationErrors[0].field", is("dateTo")))
                .andExpect(jsonPath("validationErrors[0].message", is("Cannot be smaller than dateFrom")))
                .andReturn();
    }
}
