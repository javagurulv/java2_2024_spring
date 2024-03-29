package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFileReaderTest {

    private JsonFileReader reader = new JsonFileReader();

    @Test
    public void JsonFileReader_ShouldReturnCorrectResult() {
        String expectedJson = """
                {"personFirstName" : null,
                "personLastName" : "Bērziņš",
                "agreementDateFrom" : "2025-03-10",
                "agreementDateTo" : "2025-03-11"
                }""";
        String actualJson = reader.readJsonFromFile("ControllerTest_1.1_Request_personFirstName_missing.json");

        // Formatting tolerance
        String formattedExpectedJson = expectedJson.replaceAll("\\s", "");
        String formattedActualJson = actualJson.replaceAll("\\s", "");

        assertEquals(formattedExpectedJson, formattedActualJson);
    }

}

