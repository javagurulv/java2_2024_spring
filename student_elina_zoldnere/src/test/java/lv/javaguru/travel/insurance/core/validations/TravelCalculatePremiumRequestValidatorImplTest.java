package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorImplTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private RequestFieldValidation validation1;
    @Mock
    private RequestFieldValidation validation2;
    @Mock
    private List<RequestFieldValidation> fieldValidationMock;

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void validate_ShouldPassWhenAllValidationsSucceed() {
        when(validation1.validateSingle(requestMock)).thenReturn(Optional.empty());
        when(validation2.validateList(requestMock)).thenReturn(List.of());

        List<ValidationError> errors = requestValidator.validate(requestMock);

        assertEquals(0, errors.size());
    }

    @Test
    public void validate_ShouldReturnErrorsWhenAllValidationsFail() {
        AtomicInteger invocationCount = new AtomicInteger(0);

        // It turns out being not so easy to call stream() on the same mock object multiple times AND with
        // different outputs.
        when(fieldValidationMock.stream()).thenAnswer(invocation -> {
            if (invocationCount.getAndIncrement() == 0) {
                return Stream.of(validation1); // first invocation
            } else {
                return Stream.of(validation2); // second invocation
            }
        });

        when(validation1.validateSingle(requestMock))
                .thenReturn(Optional.of(new ValidationError("ERROR_CODE_1", "Description 1")));

        when(validation2.validateList(requestMock))
                .thenReturn(Arrays.asList(
                        new ValidationError("ERROR_CODE_2", "Description 2"),
                        new ValidationError("ERROR_CODE_3", "Description 3")));

        List<ValidationError> errors = requestValidator.validate(requestMock);

        assertEquals(3, errors.size());
    }

}
