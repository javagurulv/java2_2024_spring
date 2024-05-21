package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.medical.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.medical.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCoefficientRetrieverTest {

    @Mock
    private DateTimeUtil dateTimeUtilMock;
    @Mock
    private AgeCoefficientRepository ageCoefficientRepositoryMock;

    @InjectMocks
    private AgeCoefficientRetriever ageCoefficientRetriever;

    @Autowired
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void setAgeCoefficient_shouldFindCoefficientWhenCoefficientExists() {
        ReflectionTestUtils.setField(ageCoefficientRetriever, "ageCoefficientEnabled", Boolean.TRUE);
        PersonDTO person = helper.newPersonDTO();
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.1);

        AgeCoefficient ageCoefficientMock = mock(AgeCoefficient.class);
        when(ageCoefficientRepositoryMock.findCoefficient(any()))
                .thenReturn(Optional.of(ageCoefficientMock));
        when(ageCoefficientMock.getCoefficient()).thenReturn(ageCoefficient);

        BigDecimal actualAgeCoefficient = ageCoefficientRetriever.setAgeCoefficient(person);
        assertEquals(ageCoefficient, actualAgeCoefficient);
    }

    @Test
    void setAgeCoefficient_shouldThrowExceptionWhenCoefficientDoesNotExist() {
        ReflectionTestUtils.setField(ageCoefficientRetriever, "ageCoefficientEnabled", Boolean.TRUE);
        PersonDTO person = helper.newPersonDTO();

        when(dateTimeUtilMock.calculateDifferenceBetweenDatesInYears(any(), any())).thenReturn(160);
        when(ageCoefficientRepositoryMock.findCoefficient(any()))
                .thenReturn(Optional.empty());

        Integer age = dateTimeUtilMock.calculateDifferenceBetweenDatesInYears(any(), any());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ageCoefficientRetriever
                .setAgeCoefficient(person));
        assertEquals("Coefficient for age = 160 not found!", exception.getMessage());
    }

    @Test
    void setAgeCoefficient_shouldReturnDefaultValueWhenAgeCoefficientDisabled() {
        ReflectionTestUtils.setField(ageCoefficientRetriever, "ageCoefficientEnabled", Boolean.FALSE);
        PersonDTO person = helper.newPersonDTO();

        BigDecimal actualAgeCoefficient = ageCoefficientRetriever.setAgeCoefficient(person);
        assertEquals(BigDecimal.ONE, actualAgeCoefficient);
    }

}
