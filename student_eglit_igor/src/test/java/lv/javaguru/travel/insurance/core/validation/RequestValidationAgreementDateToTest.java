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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RequestValidationAgreementDateToTest {
    @Mock
    private ErrorCodeService errorCodeService;
    @InjectMocks
    private RequestValidationAgreementDateTo requestValidationAgreementDateTo;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequest();
        when(errorCodeService.getErrorCodeDescription("ERROR_CODE_4")).thenReturn("Field agreementDateTo is empty!");
    }

    @Test
    void shouldReturnErrorWhenAgreementDateToIsNull() {
        request.setAgreementDateTo(null);
        Optional<ValidationError> error = requestValidationAgreementDateTo.executeValidation(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_4", error.get().getErrorCode());
        assertEquals("Field agreementDateTo is empty!", error.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateToIsNotNull() {
        request.setAgreementDateTo(LocalDate.now().plusDays(1));
        Optional<ValidationError> error = requestValidationAgreementDateTo.executeValidation(request);
        assertTrue(error.isEmpty());
    }
}