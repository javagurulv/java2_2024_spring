package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BirthDateValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private BirthDateValidator birthDateValidator;

    @Test
    public void noErrorIfBirthDateIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonBirthDate()).thenReturn(new Date());
        Optional<ValidationErrors> errorsOptional = birthDateValidator.validate(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }

    @Test
    public void errorIfBirthDateIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonBirthDate()).thenReturn(null);
        when(validationErrorFactory.createError("ERROR_CODE_11"))
                .thenReturn(new ValidationErrors("ERROR_CODE_11", "Birth date can't be empty if TRAVEL_MEDICAL is active"));
        Optional<ValidationErrors> errorsOptional = birthDateValidator.validate(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals("ERROR_CODE_11", errorsOptional.get().getErrorCode());
        assertEquals("Birth date can't be empty if TRAVEL_MEDICAL is active", errorsOptional.get().getDescription());
    }
}
