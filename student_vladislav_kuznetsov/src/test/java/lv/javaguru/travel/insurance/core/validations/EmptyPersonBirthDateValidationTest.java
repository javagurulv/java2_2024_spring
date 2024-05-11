package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmptyPersonBirthDateValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyPersonBirthDateValidation validation;

    @Test
    public void shouldReturnNoErrorWhenPersonBirthDateIsPresent(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonBirthDate()).thenReturn(new Date());
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertFalse(errorOpt.isPresent());

    }
    @Test
    public void shouldReturnErrorWhenPersonBirthDateIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonBirthDate()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_11"))
                .thenReturn(new ValidationError("ERROR_CODE_11", "Person birth day must not be empty!"));
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_11", errorOpt.get().getErrorCode());
        assertEquals("Person birth day must not be empty!", errorOpt.get().getDescription());
    }
}
