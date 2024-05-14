package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Test
    void validationSucceedTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidator requestValidator1 = mock(TravelRequestValidator.class);
        when(requestValidator1.validate(request)).thenReturn(Optional.empty());
        TravelRequestValidator requestValidator2 = mock(TravelRequestValidator.class);
        when(requestValidator2.validate(request)).thenReturn(Optional.empty());

        List<TravelRequestValidator> travelRequestValidators = List.of(requestValidator1, requestValidator2);

        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelRequestValidators);

        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void validationFailedTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidator requestValidator1 = mock(TravelRequestValidator.class);
        when(requestValidator1.validate(request)).thenReturn(Optional.of(new ValidationError()));
        TravelRequestValidator requestValidator2 = mock(TravelRequestValidator.class);
        when(requestValidator2.validate(request)).thenReturn(Optional.of(new ValidationError()));

        List<TravelRequestValidator> travelRequestValidators = List.of(requestValidator1, requestValidator2);

        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelRequestValidators);

        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }



}
