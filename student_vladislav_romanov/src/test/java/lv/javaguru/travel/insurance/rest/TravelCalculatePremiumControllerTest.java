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
        executeTest(
                "rest/simpleRestControllerTest_request.json",
                "rest/simpleRestControllerTest_response.json"
        );
    }

    @Test
    public void incorrectFirstNameTest() throws Exception {
        executeTest(
                "rest/incorrectFirstNameTest_request.json",
                "rest/incorrectFirstNameTest_response.json"
        );
    }

    @Test
    public void incorrectLastNameTest() throws Exception {
        executeTest(
                "rest/incorrectLastNameTest_request.json",
                "rest/incorrectLastNameTest_response.json"
        );
    }

    @Test
    public void incorrectDateFromTest() throws Exception {
        executeTest(
                "rest/incorrectDateFromTest_request.json",
                "rest/incorrectDateFromTest_response.json"
        );
    }

    @Test
    public void incorrectDateToTest() throws Exception {
        executeTest(
                "rest/incorrectDateToTest_request.json",
                "rest/incorrectDateToTest_response.json"
        );
    }

    @Test
    public void incorrectPeriodTest() throws Exception {
        executeTest(
                "rest/incorrectPeriodTest_request.json",
                "rest/incorrectPeriodTest_response.json"
        );
    }

    @Test
    public void incorrectRequestTest() throws Exception {
        executeTest(
                "rest/incorrectRequestTest_request.json",
                "rest/incorrectRequestTest_response.json"
        );
    }

    private void executeTest(String requestJsonPath, String responseJsonPath) throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                .content(getDataFromJson(requestJsonPath))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(getDataFromJson(responseJsonPath)))
                .andReturn();
    }

    private String getDataFromJson(String path) {
        return jsonFileReader.readJsonFromFile(path);
    }

}
