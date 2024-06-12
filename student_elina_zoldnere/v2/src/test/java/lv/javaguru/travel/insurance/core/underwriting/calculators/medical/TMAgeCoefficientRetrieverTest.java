package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.medical.TMAgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.medical.TMAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TMAgeCoefficientRetrieverTest {

    @Mock
    private DateTimeUtil dateTimeUtilMock;
    @Mock
    private TMAgeCoefficientRepository ageCoefficientRepositoryMock;

    @InjectMocks
    private TMAgeCoefficientRetriever ageCoefficientRetriever;

    @Test
    void setAgeCoefficient_shouldFindCoefficientWhenCoefficientExists() {
        ReflectionTestUtils.setField(ageCoefficientRetriever, "ageCoefficientEnabled", Boolean.TRUE);
        PersonDTO person = PersonDTOBuilder.createPerson().build();
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.1);

        TMAgeCoefficient ageCoefficientMock = mock(TMAgeCoefficient.class);
        when(ageCoefficientRepositoryMock.findCoefficient(any()))
                .thenReturn(Optional.of(ageCoefficientMock));
        when(ageCoefficientMock.getCoefficient()).thenReturn(ageCoefficient);

        BigDecimal actualAgeCoefficient = ageCoefficientRetriever.setAgeCoefficient(person);

        assertThat(actualAgeCoefficient).isEqualTo(ageCoefficient);
    }

    @Test
    void setAgeCoefficient_shouldThrowExceptionWhenCoefficientDoesNotExist() {
        ReflectionTestUtils.setField(ageCoefficientRetriever, "ageCoefficientEnabled", Boolean.TRUE);
        PersonDTO person = PersonDTOBuilder.createPerson().build();

        when(dateTimeUtilMock.calculateDifferenceBetweenDatesInYears(any(), any())).thenReturn(160);
        when(ageCoefficientRepositoryMock.findCoefficient(any()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> ageCoefficientRetriever.setAgeCoefficient(person))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Coefficient for age = 160 not found!");
    }

    @Test
    void setAgeCoefficient_shouldReturnDefaultValueWhenAgeCoefficientDisabled() {
        ReflectionTestUtils.setField(ageCoefficientRetriever, "ageCoefficientEnabled", Boolean.FALSE);
        PersonDTO person = PersonDTOBuilder.createPerson().build();

        BigDecimal actualAgeCoefficient = ageCoefficientRetriever.setAgeCoefficient(person);

        assertThat(actualAgeCoefficient).isEqualTo(BigDecimal.ONE);
    }

}
