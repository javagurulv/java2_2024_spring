package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {

    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
    TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
    ValidationError validationError = new ValidationError();
    List<ValidationError> errors = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        validationError.setMessage("Must not be empty!");
        errors.add(0,validationError);
        request.setPersonFirstName("");
        request.setPersonLastName("");
    }
    @Test
    public void checkValidatorErrorResponseWhenRequestFirstNameIsEmpty(){
        validationError.setField("personFirstName");
        assertEquals(errors.get(0).getField(),requestValidator.validate(request).get(0).getField());
        assertEquals(errors.get(0).getMessage(), requestValidator.validate(request).get(0).getMessage());
    }
    @Test
    public void checkValidatorErrorResponseWhenRequestLastNameIsEmpty(){
        validationError.setField("personLastName");
        assertEquals(errors.get(0).getField(),requestValidator.validate(request).get(1).getField());
        assertEquals(errors.get(0).getMessage(), requestValidator.validate(request).get(1).getMessage());
    }

}




