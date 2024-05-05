package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {

    @Mock MedicalRiskCalculator medicalRiskCalculator;
    @Mock CancellationRiskCalculator cancellationRiskCalculator;

    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwriting;

    @Test
    void travelPremiumTest() {
        BigDecimal expected = new BigDecimal(2);

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        var travelRiskPremiumCalculators = List.of(medicalRiskCalculator, cancellationRiskCalculator);
        ReflectionTestUtils.setField(travelPremiumUnderwriting, "travelRiskPremiumCalculators", travelRiskPremiumCalculators);
        when(medicalRiskCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(cancellationRiskCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);

        BigDecimal actual = travelPremiumUnderwriting.calculatePremium(request);

        assertEquals(expected, actual);
    }

}
