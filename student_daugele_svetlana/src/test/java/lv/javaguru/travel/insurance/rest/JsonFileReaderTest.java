package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFileReaderTest {
    private final JsonFileReader reader = new JsonFileReader();

    @Test
    public void JsonFileReader_ShouldReturnCorrectResult() throws JsonProcessingException {
        String expectedJson = """
                {"personFirstName" : null,
                "personLastName" : "Pupkin",
                "agreementDateFrom" : "2021-05-25",
                "agreementDateTo" : "2021-05-29"
                }""";

        String actualJson = reader.readJsonFromFile("TravelCalculatePremiumRequest_firstName_not_provided.json");

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(actualJson));
    }

}
