package lv.javaguru.travel.insurance.rest;

import org.hamcrest.Matchers;
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


    @Autowired private MockMvc mockMvc;

    @Test
    @DisplayName("Test case 1: firstName is not provided")
    public void firstNameNotProvided() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : \"Kovss\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(Matchers.nullValue())))
                .andExpect(jsonPath("personLastName", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementPrice", is(Matchers.nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 2: lastName is not provided")
    public void lastNameNotProvided() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Valera\",\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(Matchers.nullValue())))
                .andExpect(jsonPath("personLastName", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementPrice", is(Matchers.nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom is not provided")
    public void agreementDateFromNotProvided() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Valera\",\n" +
                                "\"personLastName\" : \"Kovss\",\n" +
                                "\"agreementDateFrom\" : null,\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(Matchers.nullValue())))
                .andExpect(jsonPath("personLastName", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementPrice", is(Matchers.nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo is not provided")
    public void agreementDateToNotProvided() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Valera\",\n" +
                                "\"personLastName\" : \"Kovss\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : null\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(Matchers.nullValue())))
                .andExpect(jsonPath("personLastName", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementPrice", is(Matchers.nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 5: all fields is not provided")
    public void allFieldsNotProvided() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : null,\n" +
                                "\"agreementDateTo\" : null\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(Matchers.nullValue())))
                .andExpect(jsonPath("personLastName", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementPrice", is(Matchers.nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(4)))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo < agreementDateFrom")
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Valera\",\n" +
                                "\"personLastName\" : \"Kovss\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-20\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(Matchers.nullValue())))
                .andExpect(jsonPath("personLastName", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(Matchers.nullValue())))
                .andExpect(jsonPath("agreementPrice", is(Matchers.nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must be less then agreementDateTo!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 7: success")
    public void success() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Valera\",\n" +
                                "\"personLastName\" : \"Kovss\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Valera")))
                .andExpect(jsonPath("personLastName", is("Kovss")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(0)))
                .andExpect(jsonPath("errors", is(Matchers.nullValue())))
                .andReturn();
    }

}