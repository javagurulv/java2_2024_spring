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

class RequestValidationAgreementDateToTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationAgreementDateTo requestValidationAgreementDateTo;
    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequestV1();
        when(validationErrorFactory.buildError("ERROR_CODE_4")).thenReturn(new ValidationError("ERROR_CODE_4","Field agreementDateTo is empty!"));
    }

    @Test
    void shouldReturnErrorWhenAgreementDateToIsNull() {
        request.setAgreementDateTo(null);
        Optional<ValidationError> error = requestValidationAgreementDateTo.validateSingle(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_4", error.get().getErrorCode());
        assertEquals("Field agreementDateTo is empty!", error.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateToIsNotNull() {
        request.setAgreementDateTo(LocalDate.now().plusDays(1));
        Optional<ValidationError> error = requestValidationAgreementDateTo.validateSingle(request);
        assertTrue(error.isEmpty());
    }
}