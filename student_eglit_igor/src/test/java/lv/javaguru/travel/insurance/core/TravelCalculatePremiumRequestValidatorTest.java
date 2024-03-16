package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumRequestValidatorTest {

    TravelCalculatePremiumRequest request;
    TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    public void setUp(){
        validator = new TravelCalculatePremiumRequestValidator();
        request = new TravelCalculatePremiumRequest(
                "Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13));
    }

    @Test
    void validateNoErrorsWithAllFields() {
        assertEquals(0, validator.validate(request).size());
    }

    @Test
    void validatePersonFirstNameIsNull() {
        request.setPersonFirstName(null);
        assertEquals(1, validator.validate(request).size(), "First Name null");
        assertEquals("personFirstName", validator.validate(request).get(0).getField());
        assertEquals("Must not be empty!", validator.validate(request).get(0).getMessage());
    }

    @Test
    void validatePersonFirstNameIsEmpty() {
        request.setPersonFirstName("");
        assertEquals(1, validator.validate(request).size(), "First Name empty");
        assertEquals("personFirstName", validator.validate(request).get(0).getField());
        assertEquals("Must not be empty!", validator.validate(request).get(0).getMessage());
    }

    @Test
    void validatePersonLastNameIsNull() {
        request.setPersonLastName(null);
        assertEquals(1, validator.validate(request).size(), "Last Name null");
        assertEquals("personLastName", validator.validate(request).get(0).getField());
        assertEquals("Must not be empty!", validator.validate(request).get(0).getMessage());
    }

    @Test
    void validatePersonLastNameIsEmpty() {
        request.setPersonLastName("");
        assertEquals(1, validator.validate(request).size(), "Last Name empty");
        assertEquals("personLastName", validator.validate(request).get(0).getField());
    }

    @Test
    void validateAgreementDateFromIsNull() {
        request.setAgreementDateFrom(null);
        assertEquals(1, validator.validate(request).size(), "Date From null");
        assertEquals("agreementDateFrom", validator.validate(request).get(0).getField());
        assertEquals("Must not be empty!", validator.validate(request).get(0).getMessage());
    }

    @Test
    void validateAgreementDateToIsEmpty() {
        request.setAgreementDateTo(null);
        assertEquals(1, validator.validate(request).size(), "Date To null");
        assertEquals("agreementDateTo", validator.validate(request).get(0).getField());
        assertEquals("Must not be empty!", validator.validate(request).get(0).getMessage());
    }

    @Test
    void validateAgreementDateIsAfterDateFrom() {
        request.setAgreementDateTo(LocalDate.of(2024, 12, 11));
        assertEquals(1, validator.validate(request).size(), "Date To is before Date From");
        assertEquals("agreementDateTo", validator.validate(request).get(0).getField());
        assertEquals("agreementDateTo must be after agreementDateFrom!", validator.validate(request).get(0).getMessage());
    }
}