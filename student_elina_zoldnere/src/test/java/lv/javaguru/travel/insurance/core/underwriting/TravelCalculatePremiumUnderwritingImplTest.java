package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwritingImpl;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
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
public class TravelCalculatePremiumUnderwritingImplTest {

    @Mock
    private DateTimeUtil dateTimeUtilMock;

    @InjectMocks
    private TravelCalculatePremiumUnderwritingImpl calculateUnderwriting;

    @Test
    public void calculateAgreementPrice_ShouldReturnCorrectResult() {
        when(dateTimeUtilMock.calculateDifferenceBetweenDays(any(), any())).thenReturn(1L);

        // Date calculation logic is anyway mocked one step before
        BigDecimal result = calculateUnderwriting.calculateAgreementPrice(null, null);

        assertEquals(new BigDecimal("1"), result);
    }

}