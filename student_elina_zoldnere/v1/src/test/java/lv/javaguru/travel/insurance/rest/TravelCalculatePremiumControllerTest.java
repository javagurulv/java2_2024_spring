package lv.javaguru.travel.insurance.rest;
;
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
class TravelCalculatePremiumControllerTest {

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

                Arguments.of("1.1 personFirstName null",
                        "ControllerTest_1.1_Request_personFirstName_null.json",
                        "ControllerTest_1.1_Response_personFirstName_null.json"),

                Arguments.of("1.2 personLastName null",
                        "ControllerTest_1.2_Request_personLastName_null.json",
                        "ControllerTest_1.2_Response_personLastName_null.json"),

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

                Arguments.of("1.7 agreementDateFrom null",
                        "ControllerTest_1.7_Request_agreementDateFrom_null.json",
                        "ControllerTest_1.7_Response_agreementDateFrom_null.json"),

                Arguments.of("1.8 agreementDateTo null",
                        "ControllerTest_1.8_Request_agreementDateTo_null.json",
                        "ControllerTest_1.8_Response_agreementDateTo_null.json"),

                Arguments.of("1.9 selectedRisks null",
                        "ControllerTest_1.9_Request_selectedRisks_null.json",
                        "ControllerTest_1.9_Response_selectedRisks_null.json"),

                Arguments.of("1.10 selectedRisks empty",
                        "ControllerTest_1.10_Request_selectedRisks_empty.json",
                        "ControllerTest_1.10_Response_selectedRisks_empty.json"),

                Arguments.of("1.11 country null",
                        "ControllerTest_1.11_Request_country_null.json",
                        "ControllerTest_1.11_Response_country_null.json"),

                Arguments.of("1.12 country blank",
                        "ControllerTest_1.12_Request_country_blank.json",
                        "ControllerTest_1.12_Response_country_blank.json"),

                Arguments.of("1.14 personBirthDate null",
                        "ControllerTest_1.14_Request_personBirthDate_null.json",
                        "ControllerTest_1.14_Response_personBirthDate_null.json"),

                Arguments.of("1.15 medicalRiskLimitLevel null when TRAVEL_MEDICAL risk selected",
                        "ControllerTest_1.15_Request_medicalRiskLimitLevel_null_when_TRAVEL_MEDICAL_selected.json",
                        "ControllerTest_1.15_Response_medicalRiskLimitLevel_null_when_TRAVEL_MEDICAL_selected.json"),

                Arguments.of("1.16 medicalRiskLimitLevel blank when TRAVEL_MEDICAL risk selected",
                        "ControllerTest_1.16_Request_medicalRiskLimitLevel_blank_when_TRAVEL_MEDICAL_selected.json",
                        "ControllerTest_1.16_Response_medicalRiskLimitLevel_blank_when_TRAVEL_MEDICAL_selected.json"),

                Arguments.of("1.17 medicalRiskLimitLevel null when TRAVEL_MEDICAL risk not selected",
                        "ControllerTest_1.17_Request_medicalRiskLimitLevel_null_when_TRAVEL_MEDICAL_not_selected.json",
                        "ControllerTest_1.17_Response_medicalRiskLimitLevel_null_when_TRAVEL_MEDICAL_not_selected.json"),

                Arguments.of("1.99 all fields null or empty or blank",
                        "ControllerTest_1.99_Request_all_fields_null_or_empty_or_blank.json",
                        "ControllerTest_1.99_Response_all_fields_null_or_empty_or_blank.json"),

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

                Arguments.of("2.5 personBirthDate is after the current date",
                        "ControllerTest_2.5_Request_personBirthDate_after_current_date.json",
                        "ControllerTest_2.5_Response_personBirthDate_after_current_date.json"),

                Arguments.of("2.6 personBirthDate is less than minimal date",
                        "ControllerTest_2.6_Request_personBirthDate_less_than_minimal_date.json",
                        "ControllerTest_2.6_Response_personBirthDate_less_than_minimal_date.json"),

                Arguments.of("3.1 selectedRisks not supported",
                        "ControllerTest_3.1_Request_selectedRisks_not_supported.json",
                        "ControllerTest_3.1_Response_selectedRisks_not_supported.json"),

                Arguments.of("3.2 country not supported",
                        "ControllerTest_3.2_Request_country_not_supported.json",
                        "ControllerTest_3.2_Response_country_not_supported.json"),

                Arguments.of("3.3 medical risk limit level not supported",
                        "ControllerTest_3.3_Request_medicalRiskLimitLevel_not_supported.json",
                        "ControllerTest_3.3_Response_medicalRiskLimitLevel_not_supported.json"),

                Arguments.of("9.1 all fields are present and valid",
                        "ControllerTest_9.1_Request_all_fields_present_and_valid.json",
                        "ControllerTest_9.1_Response_all_fields_present_and_valid.json")
        );
    }

    private void calculateAndCompareResponse(String requestFile, String expectedResponseFile) throws Exception {

        MockHttpServletResponse calculatedResponse = mockMvc.perform(post("/insurance/travel/api/")
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