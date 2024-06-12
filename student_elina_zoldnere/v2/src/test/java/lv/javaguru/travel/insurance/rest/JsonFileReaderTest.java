package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFileReaderTest {

    private final JsonFileReader reader = new JsonFileReader();

    @Test
    void JsonFileReader_ShouldReturnCorrectResult() throws JsonProcessingException {
        String expectedJson = """
                {
                    "request" : {
                        "personFirstName" : null,
                        "personLastName" : "Bērziņš",
                        "personalCode": "123456-12345",
                        "personBirthDate" : "1990-01-01",
                        "agreementDateFrom" : "2025-03-10",
                        "agreementDateTo" : "2025-03-11",
                        "selectedRisks" : ["TRAVEL_MEDICAL", "TRAVEL_LOSS_BAGGAGE"],
                        "country" : "SPAIN",
                        "medicalRiskLimitLevel" : "LEVEL_15000"
                    },
                    "expectedResponse": {
                        "errors": [
                          {
                            "errorCode": "ERROR_CODE_1",
                            "description": "Field personFirstName is empty!"
                          }
                        ]
                    }
                }""";

        String actualJson = reader.readJsonFromFile("v1/person/PersonV1Test_01_personFirstName_null.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(actualJson));
    }

}

