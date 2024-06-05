package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorImplTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl travelCalculatePremiumRequestValidator;

    @Test
    public void noErrors() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation firstValidation = mock(TravelRequestValidation.class);
        when(firstValidation.validate(premiumRequest)).thenReturn(Optional.empty());
        TravelRequestValidation secondValidation = mock(TravelRequestValidation.class);
        when(secondValidation.validate(premiumRequest)).thenReturn(Optional.empty());
        List<TravelRequestValidation> travelRequestValidations = List.of(firstValidation, secondValidation);

        ReflectionTestUtils.setField(travelCalculatePremiumRequestValidator, "travelRequestValidations", travelRequestValidations);
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertTrue(validationErrors.isEmpty());
    }

    @Test
    public void error() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation firstValidation = mock(TravelRequestValidation.class);
        when(firstValidation.validate(premiumRequest)).thenReturn(Optional.of(new ValidationErrors()));
        TravelRequestValidation secondValidation = mock(TravelRequestValidation.class);
        when(secondValidation.validate(premiumRequest)).thenReturn(Optional.of(new ValidationErrors()));
        List<TravelRequestValidation> travelRequestValidations = List.of(firstValidation, secondValidation);

        ReflectionTestUtils.setField(travelCalculatePremiumRequestValidator, "travelRequestValidations", travelRequestValidations);
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(premiumRequest);
        assertEquals(validationErrors.size(), 2);
    }

}

