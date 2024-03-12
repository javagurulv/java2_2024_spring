package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

@DisplayName("Module AgreementPriceCalculator test for TravelCalculatePremiumServiceImpl")
@ExtendWith(MockitoExtension.class)//annotation tells JUnit 5 to use the MockitoExtension
// to extend the test's behavior. The MockitoExtension initializes
// fields annotated with Mockito annotations like @Mock and @InjectMocks.
class TravelCalculatePremiumServiceImplModuleTest {

    @Mock
    AgreementPriceCalculator mockAgreementPriceCalculator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumServiceImpl;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = new TravelCalculatePremiumRequest(
                "Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 22));
        MockitoAnnotations.openMocks(this);
        when(mockAgreementPriceCalculator.calculateAgreementPrice(any(TravelCalculatePremiumRequest.class)))
                .thenReturn(new BigDecimal(2));

    }

    @Test
    public void shouldCheckResponseAgreementPrice() {
        TravelCalculatePremiumResponse response = travelCalculatePremiumServiceImpl.calculatePremium(request);
        assertEquals(new BigDecimal(2), response.getAgreementPrice());
    }
}
