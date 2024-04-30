package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {

    @Mock
    DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwriting;

    @Test
    void travelPremiumTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2030, 3, 8));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2030, 3, 18));
        when(dateTimeUtil.calculateTravelPeriod(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(10);

        BigDecimal expected = new BigDecimal(10);
        BigDecimal actual = travelPremiumUnderwriting.calculatePremium(request);

        assertEquals(expected, actual);
    }

}
