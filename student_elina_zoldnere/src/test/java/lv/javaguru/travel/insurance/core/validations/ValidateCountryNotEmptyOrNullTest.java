package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateCountryNotEmptyOrNullTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateCountryNotEmptyOrNull validateCountry;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenCountryIsNull() {
        when(requestMock.getCountry()).thenReturn(null);
        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationError("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationError> result = validateCountry.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenCountryIsEmpty() {
        when(requestMock.getCountry()).thenReturn("");
        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationError("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationError> result = validateCountry.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenCountryIsBlank() {
        when(requestMock.getCountry()).thenReturn("     ");
        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationError("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationError> result = validateCountry.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenRiskTypeTravelMedicalIsNotSelected() {
        when(requestMock.getSelectedRisks())
                .thenReturn(Arrays.asList("TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));

        Optional<ValidationError> result = validateCountry.validateSingle(requestMock);

        assertTrue(result.isEmpty());
        verifyNoInteractions(errorFactoryMock);
    }

}