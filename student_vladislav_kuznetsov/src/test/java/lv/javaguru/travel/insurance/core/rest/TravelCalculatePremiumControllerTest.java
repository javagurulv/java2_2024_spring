package lv.javaguru.travel.insurance.core.rest;
import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.web.servlet.MvcResult;

import java.sql.SQLException;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;
    private static final String BASE_URL = "/insurance/travel/";


    protected abstract String getTestCaseFolderName();

    protected void executeAndCompare() throws Exception {
        executeAndCompare(
                "rest/" + getTestCaseFolderName() + "/request.json",
                "rest/" + getTestCaseFolderName() + "/response.json"
        );
    }
    private void executeAndCompare(String jsonRequestFilePath,
                                   String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseBodyContent = result.getResponse().getContentAsString();
        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);
        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonResponse);
    }
}
