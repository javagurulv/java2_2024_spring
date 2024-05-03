package lv.javaguru.travel.insurance.core.validation;


import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestValidationPersonBirthDateTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private RequestValidationPersonBirthDate requestValidationPersonBirthDate;
    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequestV1();
    }

    @Test
    void shouldReturnValidationErrorWhenPersonBirthDateIsNull() {
        request.setPersonBirthDate(null);

        when(validationErrorFactory.buildError("ERROR_CODE_12"))
                .thenReturn(new ValidationError("ERROR_CODE_12", "Field personBirthDate is empty!"));

        Optional<ValidationError> result = requestValidationPersonBirthDate.validateSingle(request);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_12", result.get().getErrorCode());
        assertEquals("Field personBirthDate is empty!", result.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenPersonBirthDateIsNotNull() {

        request.setPersonBirthDate(LocalDate.now());
        Optional<ValidationError> result = requestValidationPersonBirthDate.validateSingle(request);
        assertFalse(result.isPresent());
    }
}