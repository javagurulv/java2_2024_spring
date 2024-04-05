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
    public void firstNameValidityTest() throws Exception {
        executeTest(
                "rest/firstNameValidityTest_request.json",
                "rest/firstNameValidityTest_response.json"
        );
    }

    @Test
    public void lastNameValidityTest() throws Exception {
        executeTest(
                "rest/lastNameValidityTest_request.json",
                "rest/lastNameValidityTest_response.json"
        );
    }

    @Test
    public void dateFromExistenceTest() throws Exception {
        executeTest(
                "rest/dateFromExistenceTest_request.json",
                "rest/dateFromExistenceTest_response.json"
        );
    }

    @Test
    public void dateFromIsNotInPastTest() throws Exception {
        executeTest(
                "rest/dateFromExistenceTest_request.json",
                "rest/dateFromExistenceTest_response.json"
        );
    }

    @Test
    public void dateToExistenceTest() throws Exception {
        executeTest(
                "rest/dateToExistenceTest_request.json",
                "rest/dateToExistenceTest_response.json"
        );
    }

    @Test
    public void dateToIsNotInPastTest() throws Exception {
        executeTest(
                "rest/dateFromExistenceTest_request.json",
                "rest/dateFromExistenceTest_response.json"
        );
    }

    @Test
    public void periodValidityTest() throws Exception {
        executeTest(
                "rest/periodValidityTest_request.json",
                "rest/periodValidityTest_response.json"
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
