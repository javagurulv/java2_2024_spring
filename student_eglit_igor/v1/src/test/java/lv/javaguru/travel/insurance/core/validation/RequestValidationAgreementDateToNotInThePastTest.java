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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RequestValidationAgreementDateToNotInThePastTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationAgreementDateToNotInThePast requestValidationAgreementDateToNotInThePast;
    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequestV1();
        when(validationErrorFactory.buildError("ERROR_CODE_6")).thenReturn(new ValidationError("ERROR_CODE_6","Field agreementDateTo is in the past!"));
    }

    @Test
    void shouldReturnValidationErrorWhenAgreementDateToIsInThePast() {
        request.setAgreementDateFrom(LocalDate.now().minusDays(5));
        request.setAgreementDateTo(LocalDate.now().minusDays(3));
        Optional<ValidationError> result = requestValidationAgreementDateToNotInThePast.validateSingle(request);
        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field agreementDateTo is in the past!", result.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateToIsNotInThePast() {
        request.setAgreementDateTo(LocalDate.now().plusDays(1));
        Optional<ValidationError> result = requestValidationAgreementDateToNotInThePast.validateSingle(request);
        assertTrue(result.isEmpty());
    }
}