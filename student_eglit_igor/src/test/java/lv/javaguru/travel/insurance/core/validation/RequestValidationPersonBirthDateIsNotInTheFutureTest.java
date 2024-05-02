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

class RequestValidationPersonBirthDateIsNotInTheFutureTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private RequestValidationPersonBirthDateIsNotInTheFuture validator;
private TravelCalculatePremiumRequestV1 request;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequestV1();
    }

    @Test
    void shouldReturnValidationErrorWhenPersonBirthDateIsInTheFuture() {
        request.setPersonBirthDate(LocalDate.now().plusDays(1));
        ValidationError error = new ValidationError("ERROR_CODE_13", "Person birth date is in the future!");
        when(validationErrorFactory.buildError("ERROR_CODE_13")).thenReturn(error);

        Optional<ValidationError> result = validator.validateSingle(request);

        assertTrue(result.isPresent());
        assertEquals(error, result.get());

    }

    @Test
    void shouldReturnEmptyWhenPersonBirthDateIsToday() {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setPersonBirthDate(LocalDate.now());

        Optional<ValidationError> result = validator.validateSingle(request);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyWhenPersonBirthDateIsInThePast() {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setPersonBirthDate(LocalDate.now().minusDays(1));

        Optional<ValidationError> result = validator.validateSingle(request);

        assertFalse(result.isPresent());
    }
}
