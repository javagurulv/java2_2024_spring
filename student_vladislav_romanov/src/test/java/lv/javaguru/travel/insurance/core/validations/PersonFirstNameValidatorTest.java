package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonFirstNameValidatorTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;
    @InjectMocks
    private PersonFirstNameValidator validation;

    @Test
    void personFirstNameDoNotExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription(1)).thenReturn("Person first name must exist and not to be empty!");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), 1);
        assertEquals(errors.get().getDescription(), "Person first name must exist and not to be empty!");
    }

    @Test
    void personFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(errorCodeUtil.getErrorDescription(1)).thenReturn("Person first name must exist and not to be empty!");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), 1);
        assertEquals(errors.get().getDescription(), "Person first name must exist and not to be empty!");
    }

    @Test
    void personFirstNameIsExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }

}
