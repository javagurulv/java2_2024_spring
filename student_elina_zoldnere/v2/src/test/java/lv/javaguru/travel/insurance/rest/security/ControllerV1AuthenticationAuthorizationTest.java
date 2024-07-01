package lv.javaguru.travel.insurance.rest.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerV1AuthenticationAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCredentialsProvider")
    void restControllerV1_ShouldReturn200ForValidUserCredentials(String testName, String username, String password)
            throws Exception {
        String requestJsonString = setUpRequestDataMock();
        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(post("/insurance/travel/api/v1/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader))
                .andExpect(status().isOk());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCredentialsProvider")
    void restControllerV1_ShouldReturn401ForWrongUserCredentials(String testName, String username, String password)
            throws Exception {
        String requestJsonString = setUpRequestDataMock();
        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        mockMvc.perform(post("/insurance/travel/api/v1/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void restControllerV1_ShouldReturn401ForIncorrectHeader() throws Exception {
        String requestJsonString = setUpRequestDataMock();

        String incorrectAuthHeader = "Incorrect Authentication header";

        mockMvc.perform(post("/insurance/travel/api/v1/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, incorrectAuthHeader))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void restControllerV1_ShouldReturn401ForNoCredentials() throws Exception {
        String requestJsonString = setUpRequestDataMock();

        mockMvc.perform(post("/insurance/travel/api/v1/")
                        .content(requestJsonString)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized());
    }

    private String setUpRequestDataMock() {
        return "{\"key\":\"value\"}";
    }

    private static Stream<Arguments> validCredentialsProvider(){
        return Stream.of(
                Arguments.of("valid internal user", "internal_test_user", "javaguru4"),
                Arguments.of("valid external user", "external_test_user", "javaguru5"),
                Arguments.of("valid admin", "admin", "javaguru3")
                );
    }

    private static Stream<Arguments> invalidCredentialsProvider(){
        return Stream.of(
                Arguments.of("invalid user name", "not_valid_username", "javaguru4"),
                Arguments.of("invalid password", "internal_test_user", "notValidPassword")
        );
    }

}
