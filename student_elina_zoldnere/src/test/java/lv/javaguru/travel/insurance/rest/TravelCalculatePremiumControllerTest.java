package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonFileReader reader;

    @ParameterizedTest(name = "{0}")
    @MethodSource("testNameAndFileNameProvider")
    public void controller_ShouldReturnCorrectResponses
            (String testName, String requestFile, String expectedResponseFile) throws Exception {
        calculateAndCompareResponse(requestFile, expectedResponseFile);
    }

    private static Stream<Arguments> testNameAndFileNameProvider() {
        return Stream.of(

                Arguments.of("1.1 personFirstName missing",
                        "ControllerTest_1.1_Request_personFirstName_missing.json",
                        "ControllerTest_1.1_Response_personFirstName_missing.json"),

                Arguments.of("1.2 personLastName missing",
                        "ControllerTest_1.2_Request_personLastName_missing.json",
                        "ControllerTest_1.2_Response_personLastName_missing.json"),

                Arguments.of("1.3 personFirstName empty",
                        "ControllerTest_1.3_Request_personFirstName_empty.json",
                        "ControllerTest_1.3_Response_personFirstName_empty.json"),

                Arguments.of("1.4 personLastName empty",
                        "ControllerTest_1.4_Request_personLastName_empty.json",
                        "ControllerTest_1.4_Response_personLastName_empty.json"),

                Arguments.of("1.5 personFirstName blank",
                        "ControllerTest_1.5_Request_personFirstName_blank.json",
                        "ControllerTest_1.5_Response_personFirstName_blank.json"),

                Arguments.of("1.6 personLastName blank",
                        "ControllerTest_1.6_Request_personLastName_blank.json",
                        "ControllerTest_1.6_Response_personLastName_blank.json"),

                Arguments.of("1.7 agreementDateFrom missing",
                        "ControllerTest_1.7_Request_agreementDateFrom_missing.json",
                        "ControllerTest_1.7_Response_agreementDateFrom_missing.json"),

                Arguments.of("1.8 agreementDateTo missing",
                        "ControllerTest_1.8_Request_agreementDateTo_missing.json",
                        "ControllerTest_1.8_Response_agreementDateTo_missing.json"),

                Arguments.of("1.9 selectedRisks missing",
                        "ControllerTest_1.9_Request_selectedRisks_missing.json",
                        "ControllerTest_1.9_Response_selectedRisks_missing.json"),

                Arguments.of("1.10 selectedRisks empty",
                        "ControllerTest_1.10_Request_selectedRisks_empty.json",
                        "ControllerTest_1.10_Response_selectedRisks_empty.json"),

                Arguments.of("1.11 country missing when TRAVEL_MEDICAL risk selected",
                        "ControllerTest_1.11_Request_country_missing_when_TRAVEL_MEDICAL_selected.json",
                        "ControllerTest_1.11_Response_country_missing_when_TRAVEL_MEDICAL_selected.json"),

                Arguments.of("1.12 country blank when TRAVEL_MEDICAL risk selected",
                        "ControllerTest_1.12_Request_country_blank_when_TRAVEL_MEDICAL_selected.json",
                        "ControllerTest_1.12_Response_country_blank_when_TRAVEL_MEDICAL_selected.json"),

                Arguments.of("1.13 country missing when TRAVEL_MEDICAL risk not selected",
                        "ControllerTest_1.13_Request_country_missing_when_TRAVEL_MEDICAL_not_selected.json",
                        "ControllerTest_1.13_Response_country_missing_when_TRAVEL_MEDICAL_not_selected.json"),

                Arguments.of("1.99 all fields missing or empty or blank",
                        "ControllerTest_1.99_Request_all_fields_missing_or_empty_or_blank.json",
                        "ControllerTest_1.99_Response_all_fields_missing_or_empty_or_blank.json"),

                Arguments.of("2.1 agreementDateTo is less than agreementDateFrom",
                        "ControllerTest_2.1_Request_wrong_date_chronology.json",
                        "ControllerTest_2.1_Response_wrong_date_chronology.json"),

                Arguments.of("2.2 agreementDateTo is equals agreementDateFrom",
                        "ControllerTest_2.2_Request_agreementDateTo_equals_agreementDateFrom.json",
                        "ControllerTest_2.2_Response_agreementDateTo_equals_agreementDateFrom.json"),

                Arguments.of("2.3 agreementDateFrom is less than current date",
                        "ControllerTest_2.3_Request_agreementDateFrom_less_than_current_date.json",
                        "ControllerTest_2.3_Response_agreementDateFrom_less_than_current_date.json"),

                Arguments.of("2.4 agreementDateTo is less than current date",
                        "ControllerTest_2.4_Request_agreementDateTo_less_than_current_date.json",
                        "ControllerTest_2.4_Response_agreementDateTo_less_than_current_date.json"),

                Arguments.of("3.1 selectedRisks not supported",
                        "ControllerTest_3.1_Request_selectedRisks_not_supported.json",
                        "ControllerTest_3.1_Response_selectedRisks_not_supported.json"),

                Arguments.of("3.2 country not supported",
                        "ControllerTest_3.2_Request_country_not_supported.json",
                        "ControllerTest_3.2_Response_country_not_supported.json"),

                Arguments.of("9.1 all fields are present and valid",
                        "ControllerTest_9.1_Request_all_fields_present_and_valid.json",
                        "ControllerTest_9.1_Response_all_fields_present_and_valid.json")
        );
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

        assertJson(calculatedResponseAsString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponseAsString);
    }

}