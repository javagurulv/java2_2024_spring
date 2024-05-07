package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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

    @Autowired
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    public void validateSingle_ShouldReturnCorrectResponseWhenMedicalRiskLimitLevelIsNotSupported() {
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "INVALID",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);

        when(repositoryMock.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = new ValidationErrorDTO("ERROR_CODE_93", "description");
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_93"), anyList())).thenReturn(error);

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement);

        assertTrue(result.isPresent());
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenMedicalRiskLimitLevelExists() {
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "LEVEL_10000",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);
        when(repositoryMock.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement);

        assertFalse(result.isPresent());
    }

}