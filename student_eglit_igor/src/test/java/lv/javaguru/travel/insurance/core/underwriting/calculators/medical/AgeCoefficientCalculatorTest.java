package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCoefficientCalculatorTest {

    @Mock
    private AgeCoefficientRepository ageCoefficientRepository;

    @InjectMocks
    private AgeCoefficientCalculator calculator;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setPersonBirthDate(LocalDate.of(1990, 1, 1));
        request.setAgreementDateFrom(LocalDate.of(2023, 1, 1));

    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        ReflectionTestUtils.setField(calculator, "medicalRiskAgeCoefficientEnabled", true);
        AgeCoefficient ageCoefficient = new AgeCoefficient();
        ageCoefficient.setCoefficient(BigDecimal.valueOf(1.2));
        when(ageCoefficientRepository.findAgeCoefficientByAge(33)).thenReturn(Optional.of(ageCoefficient));

        BigDecimal result = calculator.findAgeCoefficient(request);

        assertEquals(BigDecimal.valueOf(1.2), result);
    }

    @Test
    void shouldThrowExceptionWhenAgeCoefficientNotFound() {
        ReflectionTestUtils.setField(calculator, "medicalRiskAgeCoefficientEnabled", true);
        when(ageCoefficientRepository.findAgeCoefficientByAge(33)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> calculator.findAgeCoefficient(request));
    }

    @Test
    void shouldCalculateCorrectAge() {
        int result = calculator.calculateAge(request);

        assertEquals(33, result);
    }
}