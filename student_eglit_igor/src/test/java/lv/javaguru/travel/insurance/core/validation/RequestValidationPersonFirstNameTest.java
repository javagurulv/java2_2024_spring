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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RequestValidationPersonFirstNameTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationPersonFirstName requestValidationPersonFirstName;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequest();
        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(new ValidationError("ERROR_CODE_1","Field personFirstName is empty!"));
    }

    @Test
    void shouldReturnErrorWhenPersonFirstNameIsNull(){
        request.setPersonFirstName(null);
        Optional<ValidationError> error = requestValidationPersonFirstName.executeValidation(request);
        assert error.isPresent();
        assertEquals("ERROR_CODE_1", error.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", error.get().getDescription());
    }
    @Test
    void shouldReturnErrorWhenPersonFirstNameIsEmptty(){
        request.setPersonFirstName("");
        Optional<ValidationError> error = requestValidationPersonFirstName.executeValidation(request);
        assert error.isPresent();
        assertEquals("ERROR_CODE_1", error.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", error.get().getDescription());
    }
    @Test
    void shouldReturnEmptyWhenPersonFirstNameIsNotNull(){
        request.setPersonFirstName("Igor");
        Optional<ValidationError> error = requestValidationPersonFirstName.executeValidation(request);
        assert error.isEmpty();
    }
}