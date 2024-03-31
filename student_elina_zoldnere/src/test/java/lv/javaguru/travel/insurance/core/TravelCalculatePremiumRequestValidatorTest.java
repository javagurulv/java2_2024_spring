package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ValidatePersonFirstName validatePersonFirstNameMock;
    @Mock
    private ValidatePersonLastName validatePersonLastNameMock;
    @Mock
    private ValidateAgreementDateFrom validateAgreementDateFromMock;
    @Mock
    private ValidateAgreementDateTo validateAgreementDateToMock;
    @Mock
    private ValidateAgreementDateChronology validateAgreementDateChronologyMock;
    @Mock
    private ValidateAgreementDateFromNotLessThanToday validateAgreementDateFromNotLessThanTodayMock;
    @Mock
    private ValidateAgreementDateToNotLessThanToday validateAgreementDateToNotLessThanTodayMock;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;


    @Test
    public void validate_ShouldPassWhenAllValidationsSucceed() {
        when(validatePersonFirstNameMock.validatePersonFirstName(requestMock))
                .thenReturn(Optional.empty());
        when(validatePersonLastNameMock.validatePersonLastName(requestMock))
                .thenReturn(Optional.empty());
        when(validateAgreementDateFromMock.validateAgreementDateFrom(requestMock))
                .thenReturn(Optional.empty());
        when(validateAgreementDateToMock.validateAgreementDateTo(requestMock))
                .thenReturn(Optional.empty());
        when(validateAgreementDateChronologyMock.validateAgreementDateChronology(requestMock))
                .thenReturn(Optional.empty());
        when(validateAgreementDateFromNotLessThanTodayMock.validateAgreementDateFromNotLessThanToday(requestMock))
                .thenReturn(Optional.empty());
        when(validateAgreementDateToNotLessThanTodayMock.validateAgreementDateToNotLessThanToday(requestMock))
                .thenReturn(Optional.empty());
        List<ValidationError> errors = requestValidator.validate(requestMock);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void validate_ShouldReturnErrorsWhenAllValidationsFail() {
        when(validatePersonFirstNameMock.validatePersonFirstName(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(validatePersonLastNameMock.validatePersonLastName(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(validateAgreementDateFromMock.validateAgreementDateFrom(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(validateAgreementDateToMock.validateAgreementDateTo(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(validateAgreementDateChronologyMock.validateAgreementDateChronology(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(validateAgreementDateFromNotLessThanTodayMock.validateAgreementDateFromNotLessThanToday(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        when(validateAgreementDateToNotLessThanTodayMock.validateAgreementDateToNotLessThanToday(requestMock))
                .thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = requestValidator.validate(requestMock);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 7);
    }

}
