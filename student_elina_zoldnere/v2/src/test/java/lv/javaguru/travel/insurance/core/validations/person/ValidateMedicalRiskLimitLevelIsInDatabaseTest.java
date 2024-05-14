package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.hibernate.collection.internal.PersistentSortedMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateMedicalRiskLimitLevelIsInDatabaseTest {

    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateMedicalRiskLimitLevelIsInDatabase validateRiskLimitLevel;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    public void validateSingle_ShouldReturnCorrectResponseWhenMedicalRiskLimitLevelIsNotSupported() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("INVALID")
                .build();

        when(repositoryMock.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = new ValidationErrorDTO("ERROR_CODE_93", "description");
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_93"), anyList())).thenReturn(error);

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement, person);

        assertTrue(result.isPresent());
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenMedicalRiskLimitLevelExists() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        when(repositoryMock.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement, person);

        assertFalse(result.isPresent());
    }

}