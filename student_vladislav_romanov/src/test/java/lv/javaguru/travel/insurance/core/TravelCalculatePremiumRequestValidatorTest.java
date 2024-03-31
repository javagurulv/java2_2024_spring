package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validator.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private PersonFirstNameIsExistAndNotEmpty personFirstNameIsExistAndNotEmpty;

    @Mock
    private PersonLastNameIsExistAndNotEmpty personLastNameIsExistAndNotEmpty;

    @Mock
    private DateFromIsExist dateFromIsExist;

    @Mock
    private DateFromIsNotInPast dateFromIsNotInPast;

    @Mock
    private DateToIsExist dateToIsExist;

    @Mock
    private DateToIsNotInPast dateToIsNotInPast;

    @Mock
    private TravelPeriodIsValid travelPeriodIsValid;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    void validationSucceedTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(personFirstNameIsExistAndNotEmpty.validatePersonFirstName(request)).thenReturn(Optional.empty());
        when(personLastNameIsExistAndNotEmpty.validatePersonLastName(request)).thenReturn(Optional.empty());
        when(dateFromIsExist.validateDateFrom(request)).thenReturn(Optional.empty());
        when(dateToIsExist.validateDateTo(request)).thenReturn(Optional.empty());
        when(dateFromIsNotInPast.validateDateFromIsNotInPast(request)).thenReturn(Optional.empty());
        when(dateToIsNotInPast.validateDateToIsNotInPast(request)).thenReturn(Optional.empty());
        when(travelPeriodIsValid.validateTravelPeriod(request)).thenReturn(Optional.empty());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void validationFailedTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(personFirstNameIsExistAndNotEmpty.validatePersonFirstName(request)).thenReturn(Optional.of(new ValidationError()));
        when(personLastNameIsExistAndNotEmpty.validatePersonLastName(request)).thenReturn(Optional.of(new ValidationError()));
        when(dateFromIsExist.validateDateFrom(request)).thenReturn(Optional.of(new ValidationError()));
        when(dateToIsExist.validateDateTo(request)).thenReturn(Optional.of(new ValidationError()));
        when(dateFromIsNotInPast.validateDateFromIsNotInPast(request)).thenReturn(Optional.of(new ValidationError()));
        when(dateToIsNotInPast.validateDateToIsNotInPast(request)).thenReturn(Optional.of(new ValidationError()));
        when(travelPeriodIsValid.validateTravelPeriod(request)).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 7);
    }



}
