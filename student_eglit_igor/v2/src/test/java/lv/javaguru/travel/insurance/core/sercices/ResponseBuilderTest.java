package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseBuilderTest {
    private ResponseBuilder responseBuilder;
@BeforeEach
    void setUp() {
        responseBuilder = new ResponseBuilder();
    }
    @Test
    void shouldBuildResponseWithErrors() {
        var error = new ValidationErrorDTO("Error code", "Error description");
        var result = responseBuilder.buildResponse(List.of(error));
        assertEquals(1, result.getErrors().size());
        assertEquals("Error code", result.getErrors().get(0).getErrorCode());
        assertEquals("Error description", result.getErrors().get(0).getDescription());
    }
    @Test
    void shouldBuildResponseWithAgreement() {
        var result = responseBuilder.buildResponse(new AgreementDTO());
        assertNull(result.getErrors());
        assertNotNull(result.getAgreement());
    }
}