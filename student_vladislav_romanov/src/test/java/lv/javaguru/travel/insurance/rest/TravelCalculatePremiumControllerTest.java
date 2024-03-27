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

import static org.hamcrest.Matchers.is;
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

}
