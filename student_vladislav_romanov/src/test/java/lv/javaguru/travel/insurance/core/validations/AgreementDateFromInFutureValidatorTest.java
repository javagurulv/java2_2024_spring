package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromInFutureValidatorTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;
    @InjectMocks
    private AgreementDateFromInFutureValidator validation;

    @Test
    void dateFromIsInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2019, 3, 31));
        when(errorCodeUtil.getErrorDescription(4)).thenReturn("Agreement date from cannot be in past!");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), 4);
        assertEquals(errors.get().getDescription(), "Agreement date from cannot be in past!");
    }

    @Test
    void dateFromIsNotInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2030, 3, 31));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }

}
