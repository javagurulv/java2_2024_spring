package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
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
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
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

    @Test
    public void checkValidatorErrorResponseWhenRequestFirstNameIsNull(){
        request.setPersonFirstName(null);
        validationError.setField("personFirstName");
        assertEquals(errors.get(0).getField(),requestValidator.validate(request).get(0).getField());
        assertEquals(errors.get(0).getMessage(), requestValidator.validate(request).get(0).getMessage());
    }

    @Test
    public void checkValidatorErrorResponseWhenRequestLastNameIsNull(){
        request.setPersonLastName(null);
        validationError.setField("personLastName");
        assertEquals(errors.get(0).getField(),requestValidator.validate(request).get(1).getField());
        assertEquals(errors.get(0).getMessage(), requestValidator.validate(request).get(1).getMessage());
    }

    @Test
    public void checkValidatorErrorResponseWhenRequestAgreementDateFromIsNull(){
        request.setAgreementDateFrom(null);
        validationError.setField("agreementDateFrom");
        assertEquals(errors.get(0).getField(), requestValidator.validate(request).get(2).getField());
        assertEquals(errors.get(0).getMessage(), requestValidator.validate(request).get(2).getMessage());
    }

    @Test
    public void checkValidatorErrorResponseWhenAgreementRequestDateToIsNull(){
        request.setAgreementDateTo(null);
        validationError.setField("agreementDateTo");
        assertEquals(errors.get(0).getField(), requestValidator.validate(request).get(2).getField());
        assertEquals(errors.get(0).getMessage(), requestValidator.validate(request).get(2).getMessage());
    }

    @Test
    public void checkErrorMessageCountWhenAgreementRequestAllFieldsAreNull(){
        var request = new TravelCalculatePremiumRequest();
        errors = requestValidator.validate(request);
        assertEquals(4,requestValidator.validate(request).size());
    }

    @Test
    public void checkIfErrorMessageIsPresentWhenAgreementDateFromIsInThePast(){
        request.setAgreementDateFrom(new Date(0));
        request.setAgreementDateTo(new Date());
        request.setPersonFirstName("V");
        request.setPersonLastName("K");
        assertEquals(1,requestValidator.validate(request).size());
        assertTrue(requestValidator.validate(request).get(0).getField().contains("agreementDateFrom"));
    }

    @Test
    public void checkIfNoErrorIsPresentWhenAgreementDateFromIsCurrentTime(){
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date(System.currentTimeMillis()+60000));
        request.setPersonFirstName("V");
        request.setPersonLastName("K");
        var requestValidator = new TravelCalculatePremiumRequestValidator();
        assertEquals(0,requestValidator.validate(request).size());
    }
    @Test
    public void checkIfNoErrorIsPresentWhenAgreementDateFromIsInFuture(){
        request.setAgreementDateFrom(new Date(System.currentTimeMillis()+(30*60*1000)));
        request.setAgreementDateTo(new Date(System.currentTimeMillis()+(30*60*10000)));
        request.setPersonFirstName("V");
        request.setPersonLastName("K");
        var requestValidator = new TravelCalculatePremiumRequestValidator();
        assertEquals(0,requestValidator.validate(request).size());
    }
    @Test
    public void checkIfErrorIsPresentWhenAgreementDateToEqualsAgreementDateFrom(){
        request.setAgreementDateTo(new Date());
        request.setAgreementDateFrom(new Date());
        request.setPersonFirstName("V");
        request.setPersonLastName("K");
        var requestValidator = new TravelCalculatePremiumRequestValidator();
        assertEquals(1,requestValidator.validate(request).size());
    }

    @Test
    public void checkIfErrorIsPresentWhenAgreementDateToIsBeforeAgreementDateFrom(){
        request.setAgreementDateFrom(new Date(System.currentTimeMillis()+60000));
        request.setAgreementDateTo(new Date());
        request.setPersonFirstName("V");
        request.setPersonLastName("K");
        assertEquals(1,requestValidator.validate((request)).size());
    }

    @Test
    public void checkIfErrorIsPresentWhenAgreementDateToIsPresentAndAgreementDateFromIsNull(){
        request.setAgreementDateFrom(null);
        request.setAgreementDateTo(new Date());
        request.setPersonFirstName("V");
        request.setPersonLastName("K");
        assertEquals(1,requestValidator.validate((request)).size());
    }

    @Test
    public void checkIfErrorsArePresentWhenAgreementDatesAreInThePast(){
        request.setAgreementDateTo(new Date(0));
        request.setAgreementDateFrom(new Date(0));
        request.setPersonFirstName("V");
        request.setPersonLastName("K");
        assertEquals(2,requestValidator.validate((request)).size());
    }





}




