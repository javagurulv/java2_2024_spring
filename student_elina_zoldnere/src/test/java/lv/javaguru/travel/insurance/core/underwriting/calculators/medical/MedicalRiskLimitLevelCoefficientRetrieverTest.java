package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private MedicalRiskLimitLevelRepository limitLevelRepositoryMock;

    @InjectMocks
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetriever;

    @Test
    public void findCoefficient_shouldFindCoefficientWhenCoefficientExists() {
        BigDecimal limitLevelCoefficient = BigDecimal.valueOf(1.0);

        MedicalRiskLimitLevel limitLevelMock = mock(MedicalRiskLimitLevel.class);
        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc(any()))
                .thenReturn(Optional.of(limitLevelMock));
        when(limitLevelMock.getCoefficient()).thenReturn(limitLevelCoefficient);

        BigDecimal actualLimitLevelCoefficient = limitLevelCoefficientRetriever.findLimitLevelCoefficient(requestMock);
        assertEquals(limitLevelCoefficient, actualLimitLevelCoefficient);
    }

    @Test
    public void findCoefficient_shouldThrowExceptionWhenCoefficientDoesNotExist() {
        when(requestMock.getMedicalRiskLimitLevel()).thenReturn("INVALID");
        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc(any()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> limitLevelCoefficientRetriever
                .findLimitLevelCoefficient(requestMock));
        assertEquals("Medical risk limit level = INVALID coefficient not found!", exception.getMessage());
    }

}
