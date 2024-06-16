package lv.javaguru.travel.insurance.rest.security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityBasicTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader reader;
    @Autowired
    private Gson gson;

    @Test
    void restController_ShouldReturn200ForValidUserCredentials() throws Exception {
        String requestJsonString = setUpRequestData();

        String username = "testUser";
        String password = "javaguru3";
        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader))
                .andExpect(status().isOk());
    }

    @Test
    void restController_ShouldReturn401ForWrongPassword() throws Exception {
        String requestJsonString = setUpRequestData();

        String username = "testUser";
        String password = "notValidPassword";
        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void restController_ShouldReturn401ForWrongUsername() throws Exception {
        String requestJsonString = setUpRequestData();

        String username = "notValidUsername";
        String password = "javaguru3";
        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void restController_ShouldReturn401ForIncorrectHeader() throws Exception {
        String requestJsonString = setUpRequestData();

        String incorrectAuthHeader = "Incorrect Authentication header";

        mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, incorrectAuthHeader))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void restController_ShouldReturn401ForNoCredentials() throws Exception {
        String requestJsonString = setUpRequestData();

        mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized());
    }

    private String setUpRequestData() {
        JsonObject testDataJson = gson.fromJson(reader.readJsonFromFile(
                        "security/AgreementV2Test_98_all_fields_are_present_and_valid-1.json"),
                JsonObject.class);
        JsonObject requestJson = testDataJson.getAsJsonObject("request");
        return requestJson.toString();
    }

}
