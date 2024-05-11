package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {

    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwriting;

    @Test
    void travelPremiumTest() {
        BigDecimal expected = new BigDecimal(2);

        TravelRiskPremiumCalculator medicalRiskCalculator = mock(TravelRiskPremiumCalculator.class);
        TravelRiskPremiumCalculator cancellationRiskCalculator = mock(TravelRiskPremiumCalculator.class);
        var travelRiskPremiumCalculators = List.of(medicalRiskCalculator, cancellationRiskCalculator);
        ReflectionTestUtils.setField(travelPremiumUnderwriting, "travelRiskPremiumCalculators", travelRiskPremiumCalculators);

        when(medicalRiskCalculator.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(cancellationRiskCalculator.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");

        when(medicalRiskCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(cancellationRiskCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        BigDecimal actual = travelPremiumUnderwriting.calculatePremium(request).getTotalPremium();

        assertEquals(expected, actual);
    }

}
