package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RequestValidationAgreementDateFromTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationAgreementDateFrom requestValidationAgreementDateFrom;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequest();
        when(validationErrorFactory.buildError("ERROR_CODE_3")).thenReturn(new ValidationError("ERROR_CODE_3","Field agreementDateFrom is empty!"));
    }

    @Test
    void shouldReturnValidationErrorWhenAgreementDateFromIsNull() {
        request.setAgreementDateFrom(null);
        Optional<ValidationError> result = requestValidationAgreementDateFrom.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_3", result.get().getErrorCode());
        assertEquals("Field agreementDateFrom is empty!", result.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateFromIsNotNull() {
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        Optional<ValidationError> result = requestValidationAgreementDateFrom.executeValidation(request);
        assertTrue(result.isEmpty());
    }
}