package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private DateTimeUtil dateTimeUtilMock;

    @InjectMocks
    private DayCountCalculator dayCountCalculator;

    @Test
    void calculateDayCount_shouldCalculateCorrectResult() {
        when(dateTimeUtilMock.calculateDifferenceBetweenDatesInDays(any(), any())).thenReturn(1L);

        BigDecimal expectedDayCount = BigDecimal.ONE;
        BigDecimal actualDayCount = dayCountCalculator.calculateDayCount(requestMock);

        assertEquals(expectedDayCount, actualDayCount);
    }

}
