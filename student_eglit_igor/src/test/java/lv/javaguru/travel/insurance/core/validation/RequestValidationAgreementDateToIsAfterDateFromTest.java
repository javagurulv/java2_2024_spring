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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RequestValidationAgreementDateToIsAfterDateFromTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationAgreementDateToIsAfterDateFrom requestValidationAgreementDateToIsAfterDateFrom;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequest();
        when(validationErrorFactory.buildError("ERROR_CODE_7")).thenReturn(new ValidationError("ERROR_CODE_7","Field agreementDateFrom is after agreementDateTo!"));
    }

    @Test
    void shouldReturnErrorWhenAgreementDateToIsBeforeAgreementDateFrom() {
        request.setAgreementDateTo(LocalDate.now().minusDays(1));
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        Optional<ValidationError> error = requestValidationAgreementDateToIsAfterDateFrom.executeValidation(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_7", error.get().getErrorCode());
        assertEquals("Field agreementDateFrom is after agreementDateTo!", error.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateToIsAfterAgreementDateFrom() {
        request.setAgreementDateTo(LocalDate.now().plusDays(3));
        request.setAgreementDateFrom((LocalDate.now().plusDays(1)));
        Optional<ValidationError> error = requestValidationAgreementDateToIsAfterDateFrom.executeValidation(request);
        assertTrue(error.isEmpty());
    }
}