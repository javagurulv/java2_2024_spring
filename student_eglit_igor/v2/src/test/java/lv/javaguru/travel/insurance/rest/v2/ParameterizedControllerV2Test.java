package lv.javaguru.travel.insurance.rest.v2;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
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
public class ParameterizedControllerV2Test {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private JsonFileReader jsonFileReader;

    @ParameterizedTest(name = "{0}")
    @MethodSource("testNameAndRequestResponseProvider")
    public void compareRequestAndResponse(String testName, String jsonFilePath) throws Exception {

        JsonNode jsonNode = readJsonFromFile(jsonFilePath);
        String requestJson = jsonNode.get("request").toString();
        String expectedResponseJson = jsonNode.get("expected result").toString();

        String actualResponseJson = mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponseJson, actualResponseJson, false);
    }

    public static Stream<Arguments> testNameAndRequestResponseProvider() {
        return Stream.of(
                Arguments.of("Test case 1", "rest/v2/test_case_1/data.json"),
                Arguments.of("Test case 2", "rest/v2/test_case_2/data.json"),
                Arguments.of("Test case 3", "rest/v2/test_case_3/data.json"),
                Arguments.of("Test case 4", "rest/v2/test_case_4/data.json"),
                Arguments.of("Test case 5", "rest/v2/test_case_5/data.json"),
                Arguments.of("Test case 6", "rest/v2/test_case_6/data.json"),
                Arguments.of("Test case 7", "rest/v2/test_case_7/data.json"),
                Arguments.of("Test case 8", "rest/v2/test_case_8/data.json"),
                Arguments.of("Test case 9", "rest/v2/test_case_9/data.json"),
                Arguments.of("Test case 10", "rest/v2/test_case_10/data.json"),
                Arguments.of("Test case 11", "rest/v2/test_case_11/data.json"),
                Arguments.of("Test case 12", "rest/v2/test_case_12/data.json"),
                Arguments.of("Test case 13", "rest/v2/test_case_13/data.json"),
                Arguments.of("Test case 14", "rest/v2/test_case_14/data.json"),
                Arguments.of("Test case 15", "rest/v2/test_case_15/data.json"),
                Arguments.of("Test case 16", "rest/v2/test_case_16/data.json"),
                Arguments.of("Test case 17", "rest/v2/test_case_17/data.json"),
                Arguments.of("Test case 18", "rest/v2/test_case_18/data.json"),
                Arguments.of("Test case 19", "rest/v2/test_case_19/data.json"),
                Arguments.of("Test case 20", "rest/v2/test_case_20/data.json"),
                Arguments.of("Test case 21", "rest/v2/test_case_21/data.json"),
                Arguments.of("Test case 22", "rest/v2/test_case_22/data.json"),
                Arguments.of("Test case 23", "rest/v2/test_case_23/data.json"),
                Arguments.of("Test case 24", "rest/v2/test_case_24/data.json"),
                Arguments.of("Test case 25", "rest/v2/test_case_25/data.json"),
                Arguments.of("Test case 26", "rest/v2/test_case_26/data.json"),
                Arguments.of("Test case 27", "rest/v2/test_case_27/data.json"),
                Arguments.of("Test case 28", "rest/v2/test_case_28/data.json"),
                Arguments.of("Test case 29", "rest/v2/test_case_29/data.json"),
                Arguments.of("Test case 30", "rest/v2/test_case_30/data.json"),
                Arguments.of("Test case 31", "rest/v2/test_case_31/data.json"),
                Arguments.of("Test case 32", "rest/v2/test_case_32/data.json"),
                Arguments.of("Test case 33", "rest/v2/test_case_33/data.json")
        );
    }

    private JsonNode readJsonFromFile(String filePath) throws IOException {
        String content = jsonFileReader.readJsonFromFile(filePath);
        return objectMapper.readTree(content);
    }
}