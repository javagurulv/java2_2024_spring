package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validation.*;
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
public class TravelCalculatePremiumRequestValidatorImplTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl validator;

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        RequestValidation validation1 = mock(RequestValidation.class);
        when(validation1.execute(request)).thenReturn(Optional.empty());
        RequestValidation validation2 = mock(RequestValidation.class);
        when(validation2.execute(request)).thenReturn(Optional.empty());
        List<RequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(validator, "travelValidations", travelValidations);
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        RequestValidation validation1 = mock(RequestValidation.class);
        when(validation1.execute(request)).thenReturn(Optional.of(new ValidationError()));
        RequestValidation validation2 = mock(RequestValidation.class);
        when(validation2.execute(request)).thenReturn(Optional.of(new ValidationError()));
        List<RequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(validator, "travelValidations", travelValidations);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

}