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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void simpleRestControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/simpleRestControllerTest_request.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonFileReader.readJsonFromFile("rest/simpleRestControllerTest_response.json")))
                .andReturn();
    }

    @Test
    public void incorrectFirstNameTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/incorrectFirstNameTest_request.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonFileReader.readJsonFromFile("rest/incorrectFirstNameTest_response.json")))
                .andReturn();
    }

    @Test
    public void incorrectLastNameTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/incorrectLastNameTest_request.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonFileReader.readJsonFromFile("rest/incorrectLastNameTest_response.json")))
                .andReturn();
    }

    @Test
    public void incorrectDateFromTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/incorrectDateFromTest_request.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonFileReader.readJsonFromFile("rest/incorrectDateFromTest_response.json")))
                .andReturn();
    }

    @Test
    public void incorrectDateToTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/incorrectDateToTest_request.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonFileReader.readJsonFromFile("rest/incorrectDateToTest_response.json")))
                .andReturn();
    }

    @Test
    public void incorrectPeriodTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/incorrectPeriodTest_request.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonFileReader.readJsonFromFile("rest/incorrectPeriodTest_response.json")))
                .andReturn();
    }

    @Test
    public void incorrectRequestTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(jsonFileReader.readJsonFromFile("rest/incorrectRequestTest_request.json"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonFileReader.readJsonFromFile("rest/incorrectRequestTest_response.json")))
                .andReturn();
    }

}
