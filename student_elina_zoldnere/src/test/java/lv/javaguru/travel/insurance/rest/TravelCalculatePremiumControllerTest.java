package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader reader;

    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("1.1 personFirstName missing")
    public void controller_ShouldReturnCorrectResponseWhenPersonFirstNameIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.1_Request_personFirstName_missing.json";
        String expectedResponseFile = "ControllerTest_1.1_Response_personFirstName_missing.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.2 personLastName missing")
    public void controller_ShouldReturnCorrectResponseWhenPersonLastNameIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.2_Request_personLastName_missing.json";
        String expectedResponseFile = "ControllerTest_1.2_Response_personLastName_missing.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.3 personFirstName empty")
    public void controller_ShouldReturnCorrectResponseWhenPersonFirstNameIsEmpty() throws Exception {
        String requestFile = "ControllerTest_1.3_Request_personFirstName_empty.json";
        String expectedResponseFile = "ControllerTest_1.3_Response_personFirstName_empty.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.4 personLastName empty")
    public void controller_ShouldReturnCorrectResponseWhenPersonLastNameIsEmpty() throws Exception {
        String requestFile = "ControllerTest_1.4_Request_personLastName_empty.json";
        String expectedResponseFile = "ControllerTest_1.4_Response_personLastName_empty.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.5 personFirstName blank")
    public void controller_ShouldReturnCorrectResponseWhenPersonFirstNameIsBlank() throws Exception {
        String requestFile = "ControllerTest_1.5_Request_personFirstName_blank.json";
        String expectedResponseFile = "ControllerTest_1.5_Response_personFirstName_blank.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.6 personLastName blank")
    public void controller_ShouldReturnCorrectResponseWhenPersonLastNameIsBlank() throws Exception {
        String requestFile = "ControllerTest_1.6_Request_personLastName_blank.json";
        String expectedResponseFile = "ControllerTest_1.6_Response_personLastName_blank.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.7 agreementDateFrom missing")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateFromIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.7_Request_agreementDateFrom_missing.json";
        String expectedResponseFile = "ControllerTest_1.7_Response_agreementDateFrom_missing.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.8 agreementDateTo missing")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToIsMissing() throws Exception {
        String requestFile = "ControllerTest_1.8_Request_agreementDateTo_missing.json";
        String expectedResponseFile = "ControllerTest_1.8_Response_agreementDateTo_missing.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("1.9 all fields missing or empty or blank")
    public void controller_ShouldReturnCorrectResponseWhenAllFieldsMissingOrEmptyOrBlank() throws Exception {
        String requestFile = "ControllerTest_1.9_Request_all_fields_missing_or_empty_or_blank.json";
        String expectedResponseFile = "ControllerTest_1.9_Response_all_fields_missing_or_empty_or_blank.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("2.1 agreementDateTo is less than agreementDateFrom")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToLessThanAgreementDateFrom() throws Exception {
        String requestFile = "ControllerTest_2.1_Request_wrong_date_chronology.json";
        String expectedResponseFile = "ControllerTest_2.1_Response_wrong_date_chronology.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("2.2 agreementDateTo is equals agreementDateFrom")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToIsEqualsAgreementDateFrom() throws Exception {
        String requestFile = "ControllerTest_2.2_Request_agreementDateTo_equals_agreementDateFrom.json";
        String expectedResponseFile = "ControllerTest_2.2_Response_agreementDateTo_equals_agreementDateFrom.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("2.3 agreementDateFrom is less than current date")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateFromIsLessThanToday() throws Exception {
        String requestFile = "ControllerTest_2.3_Request_agreementDateFrom_less_than_current_date.json";
        String expectedResponseFile = "ControllerTest_2.3_Response_agreementDateFrom_less_than_current_date.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("2.4 agreementDateTo is less than current date")
    public void controller_ShouldReturnCorrectResponseWhenAgreementDateToIsLessThanToday() throws Exception {
        String requestFile = "ControllerTest_2.4_Request_agreementDateTo_less_than_current_date.json";
        String expectedResponseFile = "ControllerTest_2.4_Response_agreementDateTo_less_than_current_date.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    @Test
    @DisplayName("3.1 all fields are present and valid")
    public void controller_ShouldReturnCorrectResponseWhenAllFieldsAreValid() throws Exception {
        String requestFile = "ControllerTest_3.1_Request_all_fields_present_and_valid.json";
        String expectedResponseFile = "ControllerTest_3.1_Response_all_fields_present_and_valid.json";
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    private void calculateAndCompareResponse(String requestFile, String expectedResponseFile) throws Exception {

        MockHttpServletResponse calculatedResponse = mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(requestFile))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String expectedResponseAsString = reader.readJsonFromFile(expectedResponseFile);
        String calculatedResponseAsString = calculatedResponse.getContentAsString(StandardCharsets.UTF_8);

        assertEquals(sortJsonArray(expectedResponseAsString), sortJsonArray(calculatedResponseAsString));
    }

    private JsonNode sortJsonArray(String json) throws Exception {
        JsonNode node = mapper.readTree(json);

        JsonNode errorsNode = node.get("errors");
        if (errorsNode != null && errorsNode.isArray() && !errorsNode.isEmpty()) {
            ArrayNode errorsArray = (ArrayNode) errorsNode;
            List<JsonNode> errorsList = new ArrayList<>();
            errorsArray.forEach(errorsList::add);
            errorsList.sort(Comparator.comparing(o -> o.get("field").asText()));
            errorsArray.removeAll();
            errorsList.forEach(errorsArray::add);
        }
        return node;
    }

}

