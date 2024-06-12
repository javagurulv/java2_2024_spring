package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateMedicalRiskLimitLevelIsInDatabaseTest {

    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateMedicalRiskLimitLevelIsInDatabase validate;

    @Test
    void validateSingle_ShouldReturnCorrectResponseWhenMedicalRiskLimitLevelIsNotSupported() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisk("TRAVEL_MEDICAL")
                .build();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withMedicalRiskLimitLevel("INVALID")
                .build();

        when(repositoryMock.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "INVALID"))
                .thenReturn(Optional.empty());

        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_93"), anyList()))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_93", "description"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(error -> {
                    assertThat(error.errorCode()).isEqualTo("ERROR_CODE_93");
                    assertThat(error.description()).isEqualTo("description");
                });
    }

    @Test
    void validateSingle_ShouldNotReturnErrorWhenMedicalRiskLimitLevelExists() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisk("TRAVEL_MEDICAL")
                .build();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        when(repositoryMock.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertThat(result).isNotPresent();
    }

}