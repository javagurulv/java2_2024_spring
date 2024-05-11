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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgreementValidatorImplTest {

    @Mock
    private TravelAgreementAllFieldValidator agreementAllFieldValidator;
    @Mock
    private TravelPersonAllFieldValidator personAllFieldValidator;

    @InjectMocks
    private TravelAgreementValidatorImpl validator;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void validate_ShouldSumUpErrorsCorrectly() {
        AgreementDTO agreement = helper.newAgreementDTO();
        when(agreementAllFieldValidator.collectAgreementErrors(agreement))
                .thenReturn(List.of(helper.newValidationErrorDTO(), helper.newValidationErrorDTO()));
        when(personAllFieldValidator.collectPersonErrors(agreement))
                .thenReturn(List.of(helper.newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(3, errors.size());
    }

}
