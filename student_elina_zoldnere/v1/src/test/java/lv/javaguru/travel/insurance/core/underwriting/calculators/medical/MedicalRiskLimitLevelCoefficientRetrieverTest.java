package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalRiskLimitLevelCoefficientRetrieverTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private MedicalRiskLimitLevelRepository limitLevelRepositoryMock;

    @InjectMocks
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetriever;

    @Test
    void setLimitLevelCoefficient_shouldFindCoefficientWhenCoefficientExists() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        BigDecimal limitLevelCoefficient = BigDecimal.valueOf(1.2);

        MedicalRiskLimitLevel limitLevelMock = mock(MedicalRiskLimitLevel.class);
        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc(any()))
                .thenReturn(Optional.of(limitLevelMock));
        when(limitLevelMock.getCoefficient()).thenReturn(limitLevelCoefficient);

        BigDecimal actualLimitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(requestMock);
        assertEquals(limitLevelCoefficient, actualLimitLevelCoefficient);
    }

    @Test
    void setLimitLevelCoefficient_shouldThrowExceptionWhenCoefficientDoesNotExist() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        when(requestMock.getMedicalRiskLimitLevel()).thenReturn("INVALID");
        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc(any()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> limitLevelCoefficientRetriever
                .setLimitLevelCoefficient(requestMock));
        assertEquals("Medical risk limit level = INVALID coefficient not found!", exception.getMessage());
    }

    @Test
    void seLimitLevelCoefficient_shouldReturnDefaultValueWhenRiskLimitLevelDisabled() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.FALSE);

        BigDecimal actualLimitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(requestMock);
        assertEquals(BigDecimal.ONE, actualLimitLevelCoefficient);
    }

}
