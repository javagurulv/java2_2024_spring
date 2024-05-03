package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private DayCount dayCount;

    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(LocalDate.of(2025, 1, 1));
        request.setAgreementDateTo(LocalDate.of(2025, 1, 10));
    }

    @Test
    void shouldCalculateDayCountWhenDatesAreValid() {
        when(dateTimeUtil.daysCalculator(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(9L);

        BigDecimal result = dayCount.calculateDayCount(request);

        assertEquals(BigDecimal.valueOf(9), result);
    }

    @Test
    void shouldCalculateDayCountWhenDatesAreSame() {
        request.setAgreementDateTo(request.getAgreementDateFrom());
        when(dateTimeUtil.daysCalculator(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);

        BigDecimal result = dayCount.calculateDayCount(request);

        assertEquals(BigDecimal.ZERO, result);
    }
}
