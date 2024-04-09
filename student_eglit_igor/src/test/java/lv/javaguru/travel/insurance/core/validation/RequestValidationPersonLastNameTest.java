package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RequestValidationPersonLastNameTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationPersonLastName requestValidationPersonLastName;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequest();
        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(new ValidationError("ERROR_CODE_2","Field personLastName is empty!"));
    }

    @Test
    void shouldReturnErrorWhenPersonLastNameIsNull(){
        request.setPersonLastName(null);
        Optional<ValidationError> result = requestValidationPersonLastName.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_2", result.get().getErrorCode());
        assertEquals("Field personLastName is empty!", result.get().getDescription());
    }
    @Test
    void shouldReturnErrorWhenPersonLastNameIsEmpty(){
        request.setPersonLastName("");
        Optional<ValidationError> result = requestValidationPersonLastName.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_2", result.get().getErrorCode());
        assertEquals("Field personLastName is empty!", result.get().getDescription());
    }
    @Test
    void shouldReturnEmptyWhenPersonLastNameIsNotEmpty(){
        request.setPersonLastName("Igor");
        Optional<ValidationError> result = requestValidationPersonLastName.executeValidation(request);
        assertTrue(result.isEmpty());
    }
}