package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPersonAllFieldValidatorTest {

    @Mock
    private List<PersonFieldValidation> personFieldValidation;

    @InjectMocks
    private TravelPersonAllFieldValidator validator;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void collectPersonErrors_ShouldReturnErrorWhenPersonSingleValidationFail() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateSingle(any(), any()))
                .thenReturn(Optional.of(helper.newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.collectPersonErrors(agreement);

        assertEquals(1, errors.size());
    }

    @Test
    void collectPersonErrors_ShouldReturnErrorWhenPersonListValidationFail() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateList(any())).thenReturn(List.of(helper.newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.collectPersonErrors(agreement);

        assertEquals(1, errors.size());
    }

}
