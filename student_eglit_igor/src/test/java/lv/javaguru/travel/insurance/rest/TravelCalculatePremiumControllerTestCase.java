package lv.javaguru.travel.insurance.rest;


import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelCalculatePremiumControllerTestCase {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader jsonFileReader;

    protected abstract String getTestCaseFolderName();

    protected void executeAndCompare() throws Exception {
        executeAndCompare(
                "rest/" + getTestCaseFolderName() + "/request.json",
                "rest/" + getTestCaseFolderName() + "/response.json"
        );
    }

    protected void executeAndCompare(String requestPath, String responsePath) throws Exception {
        var expectedJson = getExpectedJson(responsePath);
        var actualJson = getActualJson(requestPath);
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    private String getActualJson(String filePath) throws Exception {
        return mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                // Устанавливаем заголовок Content-Type для запроса в виде JSON данных
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    private String getExpectedJson(String filePath) throws IOException {
        return jsonFileReader.readJsonFromFile(filePath);
    }
}
