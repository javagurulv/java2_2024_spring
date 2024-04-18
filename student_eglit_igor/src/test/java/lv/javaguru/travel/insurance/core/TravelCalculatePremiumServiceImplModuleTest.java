package lv.javaguru.travel.insurance.core;


import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidatorInterface;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Module AgreementPriceCalculator test for TravelCalculatePremiumServiceImpl")
@ExtendWith(MockitoExtension.class)//annotation tells JUnit 5 to use the MockitoExtension
// to extend the test's behavior. The MockitoExtension initializes
// fields annotated with Mockito annotations like @Mock and @InjectMocks.
class TravelCalculatePremiumServiceImplModuleTest {

    @Mock
    TravelPremiumUnderwriting mockAgreementPriceCalculator;
    @Mock
    TravelCalculatePremiumRequestValidatorInterface validate;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumServiceImpl;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = new TravelCalculatePremiumRequest(
                "Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 22),
                List.of("risk1", "risk2"));

        MockitoAnnotations.openMocks(this);
        when(mockAgreementPriceCalculator.calculateAgreementPremium(request))
                .thenReturn(new BigDecimal(2));
        when(validate.validate(request)).thenReturn(null);

    }

    @Test
    public void shouldCheckResponseAgreementPrice() {
        TravelCalculatePremiumResponse response = travelCalculatePremiumServiceImpl.calculatePremium(request);
        assertEquals(new BigDecimal(2), response.getAgreementPremium());
    }
}
