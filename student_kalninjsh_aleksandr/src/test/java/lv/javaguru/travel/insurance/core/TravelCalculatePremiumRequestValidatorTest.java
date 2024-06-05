package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    public void validationSucceed() {
        RequestValidation validation1 = mock(RequestValidation.class);
        when(validation1.execute(request)).thenReturn(Optional.empty());
        RequestValidation validation2 = mock(RequestValidation.class);
        when(validation2.execute(request)).thenReturn(Optional.empty());
        List<RequestValidation> requestValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(requestValidator, "requestValidations", requestValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnError() {
        RequestValidation validation1 = mock(RequestValidation.class);
        when(validation1.execute(request)).thenReturn(Optional.of(new ValidationError()));
        RequestValidation validation2 = mock(RequestValidation.class);
        when(validation2.execute(request)).thenReturn(Optional.of(new ValidationError()));
        List<RequestValidation> requestValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(requestValidator, "requestValidations", requestValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }
}