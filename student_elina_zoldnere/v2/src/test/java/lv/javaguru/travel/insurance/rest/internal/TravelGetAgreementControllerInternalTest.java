package lv.javaguru.travel.insurance.rest.internal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.EntityWriter;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TravelGetAgreementControllerInternalTest {

    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private EntityWriter entityWriter;
    @Autowired
    private JsonFileReader reader;
    @Autowired
    private Gson gson;

    private String agreementUuid;

    @Test
    public void controller_ShouldFindExistingUuid() throws Exception {
        JsonObject testDataJson = gson.fromJson(reader.readJsonFromFile(
                        "internal/ControllerInternalTest_Combined_9.01_all_fields_are_present_and_valid.json"),
                JsonObject.class);
        JsonObject setUpRequestJson = testDataJson.getAsJsonObject("set-up request");
        JsonObject expectedResponseJson = testDataJson.getAsJsonObject("expectedResponse");

        doAnswer(invocation -> {
            AgreementDTO agreement = invocation.getArgument(0);
            this.agreementUuid = agreement.uuid();
            System.out.println("Captured UUID: " + agreementUuid); // Test
            return null;
        }).when(entityWriter).writeEntities(any(AgreementDTO.class));

        MockHttpServletResponse setUpResponse = mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(setUpRequestJson.toString())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        expectedResponseJson.addProperty("uuid", agreementUuid);

        MockHttpServletResponse response = mockMvc.perform(get("/insurance/travel/api/internal/agreement/" + agreementUuid)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String expectedResponseAsString = expectedResponseJson.toString();
        String actualResponseAsString = response.getContentAsString(StandardCharsets.UTF_8);

        assertJson(actualResponseAsString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponseAsString);
    }

}
