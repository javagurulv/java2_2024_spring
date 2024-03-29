package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validation.RequestValidationInterface;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {


    @InjectMocks
    TravelCalculatePremiumRequestValidator validator;


    @Test
    void validateNoErrorsWithAllFields() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        RequestValidationInterface validation1 = mock(RequestValidationInterface.class);
        RequestValidationInterface validation2 = mock(RequestValidationInterface.class);
        when(validation1.executeValidation(request)).thenReturn(java.util.Optional.empty());
        when(validation2.executeValidation(request)).thenReturn(java.util.Optional.empty());
        List<RequestValidationInterface> validations = List.of(
                validation1, validation2);
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator(validations);
        assert (validator.validate(request).isEmpty());


    }
   @Test
    public void shouldReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        RequestValidationInterface validation1 = mock(RequestValidationInterface.class);
        when(validation1.executeValidation(request)).thenReturn(Optional.of(new ValidationError()));
        RequestValidationInterface validation2 = mock(RequestValidationInterface.class);
        when(validation2.executeValidation(request)).thenReturn(Optional.of(new ValidationError()));
        List<RequestValidationInterface> validations = List.of(
                validation1, validation2);
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator(validations);
        assert (validator.validate(request).size() == 2);
    }


   /* @Test
    void validateAgreementDateIsAfterDateFrom() {
        request.setAgreementDateTo(LocalDate.of(2024, 12, 11));
        assertEquals(1, validator.validate(request).size(), "Date To is before Date From");
        assertEquals("agreementDateTo", validator.validate(request).get(0).getField());
        assertEquals("agreementDateTo must be after agreementDateFrom!", validator.validate(request).get(0).getMessage());
    }*/
}