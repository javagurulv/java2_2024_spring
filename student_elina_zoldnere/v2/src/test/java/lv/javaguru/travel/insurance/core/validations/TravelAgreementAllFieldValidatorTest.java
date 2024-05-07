package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgreementAllFieldValidatorTest {

    @Mock
    private List<AgreementFieldValidation> agreementFieldValidation;
    @InjectMocks
    private TravelAgreementAllFieldValidator validator;

    @InjectMocks
    @Autowired
    private SetUpInstancesHelper helper;

    private AgreementDTO agreement;

    @Test
    void collectAgreementErrors_ShouldReturnErrorWhenAgreementSingleValidationFail() {
        agreement = helper.newAgreementDTO();
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.collectAgreementErrors(agreement);

        assertEquals(1, errors.size());
    }

    @Test
    void collectAgreementErrors_ShouldReturnErrorWhenAgreementListValidationFail() {
        agreement = helper.newAgreementDTO();
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateList(any())).thenReturn(List.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.collectAgreementErrors(agreement);

        assertEquals(1, errors.size());
    }

    private ValidationErrorDTO newValidationErrorDTO() {
        return new ValidationErrorDTO("errorCode", "description");
    }

}
