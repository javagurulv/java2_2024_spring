package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorImplTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private List<RequestFieldValidation> fieldValidationMock;
    @Mock
    private RequestFieldValidation validation1;
    @Mock
    private RequestFieldValidation validation2;

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Test
    public void validate_ShouldPassWhenAllValidationsSucceed() {
        when(validation1.execute(requestMock))
                .thenReturn(Optional.empty());
        when(validation2.execute(requestMock))
                .thenReturn(Optional.empty());
        when(fieldValidationMock.stream()).thenReturn(Stream.of(validation1, validation2));

        List<ValidationError> errors = requestValidator.validate(requestMock);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void validate_ShouldReturnErrorsWhenAllValidationsFail() {
        when(validation1.execute(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(validation2.execute(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(fieldValidationMock.stream()).thenReturn(Stream.of(validation1, validation2));

        List<ValidationError> errors = requestValidator.validate(requestMock);
        assertEquals(2, errors.size());
    }

}
