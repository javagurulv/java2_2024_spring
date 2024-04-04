package lv.javaguru.travel.insurance.core;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TravelCalculatePremiumRequestValidatorTest {

    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
    @Autowired TravelCalculatePremiumRequestValidator requestValidator;
    ValidationError validationError = new ValidationError();
    List<ValidationError> errors = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        validationError.setMessage("Must not be empty!");
        errors.add(0,validationError);
        request.setPersonFirstName("");
        request.setPersonLastName("");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
    }

//    @Test
//    public void checkErrorMessageCountWhenAgreementRequestAllFieldsAreNull(){
//        var request = new TravelCalculatePremiumRequest();
//        errors = requestValidator.validate(request);
//        assertEquals(4,requestValidator.validate(request).size());
//        assertNull(request.getPersonFirstName());
//        assertNull(request.getPersonLastName());
//        assertNull(request.getAgreementDateFrom());
//        assertNull(request.getAgreementDateTo());
//    }
//    @Test
//    public void checkIfErrorsArePresentWhenAgreementDatesAreInThePast(){
//        request.setAgreementDateTo(new Date(0));
//        request.setAgreementDateFrom(new Date(0));
//        request.setPersonFirstName("V");
//        request.setPersonLastName("K");
//        assertEquals(2,requestValidator.validate((request)).size());
//    }

}




