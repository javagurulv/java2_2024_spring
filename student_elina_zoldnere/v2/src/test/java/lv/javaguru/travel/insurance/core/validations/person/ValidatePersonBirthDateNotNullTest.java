package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonBirthDateNotNullTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonBirthDateNotNull validate;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromIsNull() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(null)
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        when(errorMock.buildError("ERROR_CODE_7"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_7", "Field personBirthDate is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_7", result.get().errorCode());
        assertEquals("Field personBirthDate is empty!", result.get().description());
    }

}