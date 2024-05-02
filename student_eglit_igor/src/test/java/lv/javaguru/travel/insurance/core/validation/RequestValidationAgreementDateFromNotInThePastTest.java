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
import static org.mockito.Mockito.when;

class RequestValidationAgreementDateFromNotInThePastTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationAgreementDateFromNotInThePast requestValidationAgreementDateFromNotInThePast;
    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequestV1();
        when(validationErrorFactory.buildError("ERROR_CODE_5")).thenReturn(new ValidationError("ERROR_CODE_5","Field agreementDateFrom is in the past!"));
    }

    @Test
    void shouldReturnValidationErrorWhenAgreementDateFromIsInThePast() {
        request.setAgreementDateFrom(LocalDate.now().minusDays(3));
        request.setAgreementDateTo(LocalDate.now().minusDays(2));
        Optional<ValidationError> result = requestValidationAgreementDateFromNotInThePast.validateSingle(request);
        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field agreementDateFrom is in the past!", result.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateFromIsNotNull() {
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        Optional<ValidationError> result = requestValidationAgreementDateFromNotInThePast.validateSingle(request);
        assertTrue(result.isEmpty());
    }

}