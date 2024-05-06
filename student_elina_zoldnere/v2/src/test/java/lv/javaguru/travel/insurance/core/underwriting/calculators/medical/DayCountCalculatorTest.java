package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidateSetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtilMock;

    @InjectMocks
    private DayCountCalculator dayCountCalculator;

    @Autowired
    @InjectMocks
    private ValidateSetUpInstancesHelper helper;

    @Test
    void calculateDayCount_shouldCalculateCorrectResult() {
        AgreementDTO agreement = helper.newAgreementDTO();
        when(dateTimeUtilMock.calculateDifferenceBetweenDatesInDays(any(), any())).thenReturn(1L);

        BigDecimal expectedDayCount = BigDecimal.ONE;
        BigDecimal actualDayCount = dayCountCalculator.calculateDayCount(agreement);

        assertEquals(expectedDayCount, actualDayCount);
    }

}
