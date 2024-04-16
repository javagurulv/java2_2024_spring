package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TravelCalculatePremiumRequestValidatorImplTest {

        @Mock
        private RequestValidationInterface validation1;

        @Mock
        private RequestValidationInterface validation2;

        private TravelCalculatePremiumRequestValidatorImpl validator;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            validator = new TravelCalculatePremiumRequestValidatorImpl(List.of(validation1, validation2));
        }

        @Test
        void shouldReturnNoErrorsWhenAllValidationsPass() {
            TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
            when(validation1.validateReq(request)).thenReturn(Optional.empty());
            when(validation2.validateReq(request)).thenReturn(Optional.empty());

            List<ValidationError> errors = validator.validate(request);

            assertEquals(0, errors.size());
        }

        @Test
        void shouldReturnErrorsWhenOneValidationFails() {
            TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
            ValidationError error = new ValidationError();
            when(validation1.validateReq(request)).thenReturn(Optional.of(error));
            when(validation2.validateReq(request)).thenReturn(Optional.empty());

            List<ValidationError> errors = validator.validate(request);

            assertEquals(1, errors.size());
            assertEquals(error, errors.get(0));
        }

        @Test
        void shouldReturnErrorsWhenMultipleValidationsFail() {
            TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
            ValidationError error1 = new ValidationError();
            ValidationError error2 = new ValidationError();
            when(validation1.validateReq(request)).thenReturn(Optional.of(error1));
            when(validation2.validateReq(request)).thenReturn(Optional.of(error2));

            List<ValidationError> errors = validator.validate(request);

            assertEquals(2, errors.size());
            assertEquals(List.of(error1, error2), errors);
        }

        @Test
        void shouldReturnNoErrorsWhenNoValidations() {
            validator = new TravelCalculatePremiumRequestValidatorImpl(Collections.emptyList());
            TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

            List<ValidationError> errors = validator.validate(request);

            assertEquals(0, errors.size());
        }
    }

