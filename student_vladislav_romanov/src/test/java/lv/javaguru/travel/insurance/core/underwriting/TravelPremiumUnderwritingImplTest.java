package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {

    @Mock
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwriting;

    @Test
    void travelPremiumTest() {
        BigDecimal expected = new BigDecimal(2);
        List<RiskPremium> riskPremiums = List.of(
                new RiskPremium("TRAVEL_MEDICAL", BigDecimal.ONE),
                new RiskPremium("TRAVEL_EVACUATION", BigDecimal.ONE)
        );

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(selectedRisksPremiumCalculator.calculateSelectedRisksPremiums(request)).thenReturn(riskPremiums);
        BigDecimal actual = travelPremiumUnderwriting.calculatePremium(request).getTotalPremium();

        assertEquals(expected, actual);
    }

}
