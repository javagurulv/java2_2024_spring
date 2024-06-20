package lv.javaguru.travel.insurance.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class TravelCalculatePremiumControllerTestTemplate {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected JsonFileReader reader;
    @Autowired
    protected Gson gson;
    @Autowired
    protected TestDataFileProvider fileProvider;

    protected abstract String getTestDataPath();

    protected abstract String getTestCasePrefix();

    protected abstract String getEndpoint();

    @TestFactory
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    Stream<DynamicTest> dynamicTestsFromStream() {
        return fileProvider.provideTestData(getTestDataPath())
                .map(data ->
                        DynamicTest.dynamicTest("Test Case: "
                                        + data.replace(getTestCasePrefix(), ""),
                                () -> calculateAndCompareResponse((data)))
                );
    }

    private void calculateAndCompareResponse(String fileName) throws Exception {
        JsonObject testDataJson =
                gson.fromJson(reader.readJsonFromFile(getTestDataPath() + fileName), JsonObject.class);
        JsonObject requestJson = testDataJson.getAsJsonObject("request");
        JsonObject expectedResponseJson = testDataJson.getAsJsonObject("expectedResponse");

        MockHttpServletResponse calculatedResponse = mockMvc.perform(post(getEndpoint())
                        .content(requestJson.toString())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String expectedResponseAsString = expectedResponseJson.toString();
        String calculatedResponseAsString = calculatedResponse.getContentAsString(StandardCharsets.UTF_8);

        assertJson(calculatedResponseAsString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponseAsString);
    }

}