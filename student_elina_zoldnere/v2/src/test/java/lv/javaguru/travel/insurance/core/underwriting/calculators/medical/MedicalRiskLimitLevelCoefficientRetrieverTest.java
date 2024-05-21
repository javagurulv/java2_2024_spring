package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.medical.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.medical.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
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
    private MedicalRiskLimitLevelRepository limitLevelRepositoryMock;

    @InjectMocks
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetriever;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void setLimitLevelCoefficient_shouldFindCoefficientWhenCoefficientExists() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        PersonDTO person = helper.newPersonDTO();
        BigDecimal limitLevelCoefficient = BigDecimal.valueOf(1.2);

        MedicalRiskLimitLevel limitLevelMock = mock(MedicalRiskLimitLevel.class);
        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc(any()))
                .thenReturn(Optional.of(limitLevelMock));
        when(limitLevelMock.getCoefficient()).thenReturn(limitLevelCoefficient);

        BigDecimal actualLimitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(person);
        assertEquals(limitLevelCoefficient, actualLimitLevelCoefficient);
    }

    @Test
    void setLimitLevelCoefficient_shouldThrowExceptionWhenCoefficientDoesNotExist() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("INVALID")
                .build();

        when(limitLevelRepositoryMock.findByMedicalRiskLimitLevelIc("INVALID"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> limitLevelCoefficientRetriever
                .setLimitLevelCoefficient(person));
        assertEquals("Medical risk limit level = INVALID coefficient not found!", exception.getMessage());
    }

    @Test
    void seLimitLevelCoefficient_shouldReturnDefaultValueWhenRiskLimitLevelDisabled() {
        ReflectionTestUtils.setField(limitLevelCoefficientRetriever, "medicalRiskLimitLevelEnabled", Boolean.FALSE);
        PersonDTO person = helper.newPersonDTO();

        BigDecimal actualLimitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(person);
        assertEquals(BigDecimal.ONE, actualLimitLevelCoefficient);
    }

}
