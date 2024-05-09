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

import java.awt.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
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
    public void simpleRestControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content("{" +
                        "\"personFirstName\" : \"Tom\",\n" +
                        "\"personLastName\" : \"Sawyer\",\n" +
                        "\"agreementDateFrom\" : \"2005-05-15\",\n" +
                        "\"agreementDateTo\" : \"2005-05-20\"\n" +
                        "}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Tom")))
                .andExpect(jsonPath("personLastName", is("Sawyer")))
                .andExpect(jsonPath("agreementDateFrom", is("2005-05-15")))
                .andExpect(jsonPath("agreementDateTo", is("2005-05-20")))
                .andExpect(jsonPath("agreementPrice", is(5)))
                .andReturn();
    }

    @Test
    @DisplayName("1.personFirstName is null or empty")
    public void personFirstNameIsNullOrEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content("{" +
                        "\"personFirstName\" : \"\",\n" +
                        "\"personLastName\" : \"Sawyer\",\n" +
                        "\"agreementDateFrom\" : \"2005-05-15\",\n" +
                        "\"agreementDateTo\" : \"2005-05-20\"\n" +
                        "}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andReturn();
    }

    @DisplayName("2.personLastName is null or empty")
    @Test
    public void personLastNameIsNullOrEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Tom\",\n" +
                                "\"personLastName\" : \"\",\n" +
                                "\"agreementDateFrom\" : \"2005-05-15\",\n" +
                                "\"agreementDateTo\" : \"2005-05-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andReturn();
    }

    @DisplayName("3.agreementDateFrom is null or empty")
    @Test
    public void agreementDateFromIsNullOrEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Tom\",\n" +
                                "\"personLastName\" : \"Sawyer\",\n" +
                                "\"agreementDateFrom\" : \"\",\n" +
                                "\"agreementDateTo\" : \"2005-05-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andReturn();
    }

    @DisplayName("4.agreementDateTo is null or empty")
    @Test
    public void agreementDateToIsNullOrEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Tom\",\n" +
                                "\"personLastName\" : \"Sawyer\",\n" +
                                "\"agreementDateFrom\" : \"2005-05-15\",\n" +
                                "\"agreementDateTo\" : \"\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty")))
                .andReturn();
    }

    @DisplayName("5.All fields is null or empty")
    @Test
    public void allFieldsIsNullOrEmpty() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"\",\n" +
                                "\"personLastName\" : \"\",\n" +
                                "\"agreementDateFrom\" : \"\",\n" +
                                "\"agreementDateTo\" : \"\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(4)))
                .andReturn();
    }

    @DisplayName("6.agreementDateTo less than agreementDateFrom")
    @Test
    public void agreementDateToLessThanAgreementDateFrom() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Tom\",\n" +
                                "\"personLastName\" : \"Sawyer\",\n" +
                                "\"agreementDateFrom\" : \"2005-05-20\",\n" +
                                "\"agreementDateTo\" : \"2005-05-15\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must be less then agreementDateTo")))
                .andReturn();
    }

    @DisplayName("7.All fields are specified")
    @Test
    public void allFieldsAreSpecified() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Tom\",\n" +
                                "\"personLastName\" : \"Sawyer\",\n" +
                                "\"agreementDateFrom\" : \"2005-03-15\",\n" +
                                "\"agreementDateTo\" : \"2005-05-25\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Tom")))
                .andExpect(jsonPath("personLastName", is("Sawyer")))
                .andExpect(jsonPath("agreementDateFrom", is("2005-03-15")))
                .andExpect(jsonPath("agreementDateTo", is("2005-05-25")))
                .andExpect(jsonPath("agreementPrice", is(71)))
                .andExpect(jsonPath("errors", is(nullValue())))
                .andReturn();
    }







}