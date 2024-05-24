package lv.javaguru.travel.insurance.rest.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParameterizedControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader jsonFileReader;



    @ParameterizedTest(name = "{0}")
    @MethodSource("testNameAndRequestResponseProvider")
    public void compareRequestAndResponse(String testName, String requestPath, String responsePath) throws Exception {
        var expectedJson = getExpectedJson(responsePath);
        var actualJson = getActualJson(requestPath);
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    public static Stream<Arguments> testNameAndRequestResponseProvider() {
        return Stream.of(
                Arguments.of(
                        "Empty request and response",
                        "rest/v1/test_case_1/request.json",
                        "rest/v1/test_case_1/response.json"),
                Arguments.of("Request and response with valid data", "rest/v1/test_case_2/request.json", "rest/v1/test_case_2/response.json"),
                Arguments.of("Empty country in request and response", "rest/v1/test_case_3/request.json", "rest/v1/test_case_3/response.json"),
                Arguments.of("Null country in request and response", "rest/v1/test_case_4/request.json", "rest/v1/test_case_4/response.json"),
                Arguments.of("Unsupported country in request and response", "rest/v1/test_case_5/request.json", "rest/v1/test_case_5/response.json"),
                Arguments.of("Empty travel start date", "rest/v1/test_case_6/request.json", "rest/v1/test_case_6/response.json"),
                Arguments.of("Travel start date in the past in request and response", "rest/v1/test_case_7/request.json", "rest/v1/test_case_7/response.json"),
                Arguments.of("Null travel start date in request and response", "rest/v1/test_case_8/request.json", "rest/v1/test_case_8/response.json"),
                Arguments.of("Travel end date before start date in request and response", "rest/v1/test_case_9/request.json", "rest/v1/test_case_9/response.json"),
                Arguments.of("Empty travel end date", "rest/v1/test_case_10/request.json", "rest/v1/test_case_10/response.json"),
                Arguments.of("Travel end date in the past and before start date in request and response", "rest/v1/test_case_11/request.json", "rest/v1/test_case_11/response.json"),
                Arguments.of("Missing travel end date in request and response", "rest/v1/test_case_12/request.json", "rest/v1/test_case_12/response.json"),
                Arguments.of("Empty first name in request and response", "rest/v1/test_case_13/request.json", "rest/v1/test_case_13/response.json"),
                Arguments.of("Null first name in request and response", "rest/v1/test_case_14/request.json", "rest/v1/test_case_14/response.json"),
                Arguments.of("Empty last name in request and response", "rest/v1/test_case_15/request.json", "rest/v1/test_case_15/response.json"),
                Arguments.of("Null last name in request and response", "rest/v1/test_case_16/request.json", "rest/v1/test_case_16/response.json"),
                Arguments.of("Empty medical risk level, then selected risks for travel medical exists in request and response", "rest/v1/test_case_17/request.json", "rest/v1/test_case_17/response.json"),
                Arguments.of("Empty selected risks field in request and response", "rest/v1/test_case_18/request.json", "rest/v1/test_case_18/response.json"),
                Arguments.of("Null selected risks field in request and response", "rest/v1/test_case_19/request.json", "rest/v1/test_case_19/response.json"),
                Arguments.of("Selected risks not supported in request and response", "rest/v1/test_case_20/request.json", "rest/v1/test_case_20/response.json"),
                Arguments.of("Null personCode in request", "rest/v1/test_case_21/request.json", "rest/v1/test_case_21/response.json"),
                Arguments.of("Empty personCode in request", "rest/v1/test_case_22/request.json", "rest/v1/test_case_22/response.json")
        );
    }
    private String getActualJson(String filePath) throws Exception {
        return mockMvc.perform(post("/insurance/travel/api/v1/")
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
