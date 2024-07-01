package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TCAgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TCAgeCoefficientCalculatorTest {
    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private TCAgeCoefficientRepository ageCoefficientRepository;
    @InjectMocks
    private TCAgeCoefficientCalculator calculator;

    private PersonDTO person;
    private LocalDate currentDate;

    @BeforeEach
    void setUp() {
        person = new PersonDTO(
                "Joe",
                "Doe",
                "12345",
                LocalDate.of(1991, 1, 1),
                "Limit 1000",
                new BigDecimal(1000),
                List.of()
        );
        currentDate = LocalDate.of(2024,2, 2);
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        int age = 33;
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);

        lenient().when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        TCAgeCoefficient ageCoefficient = mock(TCAgeCoefficient.class);
        lenient().when(ageCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        lenient().when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.of(ageCoefficient));

        BigDecimal result = calculator.calculate(person);

        assertEquals(result, expectedCoefficient);
    }
    @Test
    void shouldThrowExceptionWhenAgeCoefficientNotFound(){
        int age = 33;

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(currentDate);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->calculator.calculate(person));
        assertEquals("Age coefficient not found for age = " + age, exception.getMessage());
    }

}