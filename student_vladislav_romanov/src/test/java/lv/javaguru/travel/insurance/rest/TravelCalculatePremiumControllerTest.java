package lv.javaguru.travel.insurance.rest;

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
    public void simpleRestControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {
                                    "personFirstName" : "Vladislav",
                                    "personLastName" : "Romanov",
                                    "agreementDateFrom" : "2024-03-10",
                                    "agreementDateTo" : "2024-03-20"
                                }
                                """)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Vladislav")))
                .andExpect(jsonPath("personLastName", is("Romanov")))
                .andExpect(jsonPath("agreementDateFrom", is("2024-03-10")))
                .andExpect(jsonPath("agreementDateTo", is("2024-03-20")))
                .andExpect(jsonPath("agreementPrice", is(10)))
                .andReturn();
    }

    @Test
    public void incorrectFirstNameTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {
                                    "personFirstName" : "",
                                    "personLastName" : "Romanov",
                                    "agreementDateFrom" : "2024-03-10",
                                    "agreementDateTo" : "2024-03-20"
                                }
                                """)
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
                .andExpect(jsonPath("errors[0].message", is("must exist and not to be empty!")))
                .andReturn();
    }

    @Test
    public void incorrectLastNameTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {
                                    "personFirstName" : "Vladislav",
                                    "personLastName" : null,
                                    "agreementDateFrom" : "2024-03-10",
                                    "agreementDateTo" : "2024-03-20"
                                }
                                """)
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
                .andExpect(jsonPath("errors[0].message", is("must exist and not to be empty!")))
                .andReturn();
    }

    @Test
    public void incorrectDateFromTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {
                                    "personFirstName" : "Vladislav",
                                    "personLastName" : "Romanov",
                                    "agreementDateFrom" : null,
                                    "agreementDateTo" : "2024-03-20"
                                }
                                """)
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
                .andExpect(jsonPath("errors[0].message", is("must exist and not to be empty!")))
                .andExpect(jsonPath("errors[1].field", is("Travel Period")))
                .andExpect(jsonPath("errors[1].message", is("contain incorrect data!")))
                .andReturn();
    }

    @Test
    public void incorrectDateToTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {
                                    "personFirstName" : "Vladislav",
                                    "personLastName" : "Romanov",
                                    "agreementDateFrom" : "2024-03-10",
                                    "agreementDateTo" : ""
                                }
                                """)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(2)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("must exist and not to be empty!")))
                .andExpect(jsonPath("errors[1].field", is("Travel Period")))
                .andExpect(jsonPath("errors[1].message", is("contain incorrect data!")))
                .andReturn();
    }

    @Test
    public void incorrectPeriodTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {
                                    "personFirstName" : "Vladislav",
                                    "personLastName" : "Romanov",
                                    "agreementDateFrom" : "2024-03-20",
                                    "agreementDateTo" : "2024-03-10"
                                }
                                """)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("Travel Period")))
                .andExpect(jsonPath("errors[0].message", is("contain incorrect data!")))
                .andReturn();
    }

    @Test
    public void incorrectRequestTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("""
                                {
                                    "personFirstName" : "",
                                    "personLastName" : null,
                                    "agreementDateFrom" : "2024-03-20",
                                    "agreementDateTo" : "2024-03-10"
                                }
                                """)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(3)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("must exist and not to be empty!")))
                .andExpect(jsonPath("errors[1].field", is("personLastName")))
                .andExpect(jsonPath("errors[1].message", is("must exist and not to be empty!")))
                .andExpect(jsonPath("errors[2].field", is("Travel Period")))
                .andExpect(jsonPath("errors[2].message", is("contain incorrect data!")))
                .andReturn();
    }

}
