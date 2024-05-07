package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalRiskLimitLevelCoefficientRetrieverTest {

    @Mock
    private MedicalRiskLimitLevelRepository limitLevelRepositoryMock;

    @InjectMocks
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetriever;

    @Autowired
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void setLimitLevelCoefficient_shouldFindCoefficientWhenCoefficientExists() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        AgreementDTO agreement = helper.newAgreementDTO();
        BigDecimal limitLevelCoefficient = BigDecimal.valueOf(1.2);

        MedicalRiskLimitLevel limitLevelMock = mock(MedicalRiskLimitLevel.class);
        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc(any()))
                .thenReturn(Optional.of(limitLevelMock));
        when(limitLevelMock.getCoefficient()).thenReturn(limitLevelCoefficient);

        BigDecimal actualLimitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(agreement);
        assertEquals(limitLevelCoefficient, actualLimitLevelCoefficient);
    }

    @Test
    void setLimitLevelCoefficient_shouldThrowExceptionWhenCoefficientDoesNotExist() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "INVALID",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);
        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc("INVALID"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> limitLevelCoefficientRetriever
                .setLimitLevelCoefficient(agreement));
        assertEquals("Medical risk limit level = INVALID coefficient not found!", exception.getMessage());
    }

    @Test
    void seLimitLevelCoefficient_shouldReturnDefaultValueWhenRiskLimitLevelDisabled() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.FALSE);
        AgreementDTO agreement = helper.newAgreementDTO();

        BigDecimal actualLimitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(agreement);
        assertEquals(BigDecimal.ONE, actualLimitLevelCoefficient);
    }

}
