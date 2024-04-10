package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFileReaderTest {

    private final JsonFileReader reader = new JsonFileReader();

    @Test
    public void JsonFileReader_ShouldReturnCorrectResult() throws JsonProcessingException {
        String expectedJson = """
                {"personFirstName" : null,
                "personLastName" : "Bērziņš",
                "agreementDateFrom" : "2025-03-10",
                "agreementDateTo" : "2025-03-11",
                "selectedRisks" : ["TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"]
                }""";

        String actualJson = reader.readJsonFromFile("ControllerTest_1.1_Request_personFirstName_missing.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(actualJson));
    }

}

