package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)//annotation tells JUnit 5 to use the MockitoExtension
// to extend the test's behavior. The MockitoExtension initializes
// fields annotated with Mockito annotations like @Mock and @InjectMocks.
class TravelCalculatePremiumServiceImplModuleTest {

    @Mock
    AgreementPriceCalculator mockAgreementPriceCalculator;

    @InjectMocks
    TravelCalculatePremiumServiceImpl travelCalculatePremiumServiceImpl;

    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldCheckResponseAgreementPrice() {

        when(mockAgreementPriceCalculator.calculateAgreementPrice(any(TravelCalculatePremiumRequest.class)))
                .thenReturn(new BigDecimal(2));
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 22));

        TravelCalculatePremiumResponse response = travelCalculatePremiumServiceImpl.calculatePremium(request);
        assertEquals(new BigDecimal(2), response.getAgreementPrice());
    }
}
