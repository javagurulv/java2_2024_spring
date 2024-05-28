package lv.javaguru.travel.insurance.rest.v2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import lv.javaguru.travel.insurance.rest.TestDataFileProvider;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerV2Test {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader reader;
    @Autowired
    private Gson gson;
    @Autowired
    private TestDataFileProvider fileProvider;

    @TestFactory
    public Stream<DynamicTest> dynamicTestsFromStream() {
        return fileProvider.provideTestData("v2")
                .map(data ->
                        DynamicTest.dynamicTest("Test Case: "
                                        + data.replace("ControllerV2Test_", ""),
                                () -> calculateAndCompareResponse((data)))
                );
    }

    private void calculateAndCompareResponse(String fileName) throws Exception {
        JsonObject testDataJson = gson.fromJson(reader.readJsonFromFile("v2/" + fileName),  JsonObject.class);
        JsonObject requestJson = testDataJson.getAsJsonObject("request");
        JsonObject expectedResponseJson = testDataJson.getAsJsonObject("expectedResponse");

        MockHttpServletResponse calculatedResponse = mockMvc.perform(post("/insurance/travel/api/v2/")
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