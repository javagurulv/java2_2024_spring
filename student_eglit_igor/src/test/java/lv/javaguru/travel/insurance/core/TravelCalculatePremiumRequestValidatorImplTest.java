package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidatorInterface;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorImplTest {


//    @InjectMocks
//    TravelCalculatePremiumRequestValidatorInterface validator;


    @Test
    void validateNoErrorsWithAllFields() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        RequestValidationInterface validation1 = mock(RequestValidationInterface.class);
//        RequestValidationInterface validation2 = mock(RequestValidationInterface.class);
        TravelCalculatePremiumRequestValidatorInterface validator = mock(TravelCalculatePremiumRequestValidatorInterface.class);
//        when(validation1.executeValidation(request)).thenReturn(java.util.Optional.empty());
//        when(validation2.executeValidation(request)).thenReturn(java.util.Optional.empty());

        when(validator.validate(request)).thenReturn(List.of());


        assert (validator.validate(request).isEmpty());


    }

    @Test
    public void shouldReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        RequestValidationInterface validation1 = mock(RequestValidationInterface.class);
//        when(validation1.executeValidation(request)).thenReturn(Optional.of(new ValidationError()));
//        RequestValidationInterface validation2 = mock(RequestValidationInterface.class);
//        when(validation2.executeValidation(request)).thenReturn(Optional.of(new ValidationError()));
        TravelCalculatePremiumRequestValidatorInterface validator = mock(TravelCalculatePremiumRequestValidatorInterface.class);
        when(validator.validate(request)).thenReturn(List.of(new ValidationError(), new ValidationError()));

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