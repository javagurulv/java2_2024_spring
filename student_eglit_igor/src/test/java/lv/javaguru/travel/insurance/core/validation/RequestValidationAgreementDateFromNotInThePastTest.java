package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.ErrorCodeService;
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

class RequestValidationAgreementDateFromNotInThePastTest {
    @Mock
    private ErrorCodeService errorCodeService;
    @InjectMocks
    private RequestValidationAgreementDateFromNotInThePast requestValidationAgreementDateFromNotInThePast;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequest();
        when(errorCodeService.getErrorCodeDescription("ERROR_CODE_5")).thenReturn("Field agreementDateFrom is in the past!");
    }

    @Test
    void shouldReturnValidationErrorWhenAgreementDateFromIsInThePast() {
        request.setAgreementDateFrom(LocalDate.now().minusDays(3));
        request.setAgreementDateTo(LocalDate.now().minusDays(2));
        Optional<ValidationError> result = requestValidationAgreementDateFromNotInThePast.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field agreementDateFrom is in the past!", result.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateFromIsNotNull() {
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        Optional<ValidationError> result = requestValidationAgreementDateFromNotInThePast.executeValidation(request);
        assertTrue(result.isEmpty());
    }

}