package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {
    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    public void checkCalculatedAgreementPrice()
    {
        TravelCalculatePremiumRequest request = createRequestWithAllFields();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        BigDecimal agreementPrice = new BigDecimal(10);
        assertEquals(response.getAgreementPrice(),agreementPrice);
    }

    public TravelCalculatePremiumRequest createRequestWithAllFields (){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date(2024, 3, 1));
        request.setAgreementDateFrom(new Date(2024, 3, 11));
        request.setPersonFirstName("Vladislav");
        request.setPersonLastName("Kuznetsov");
        return request;
    }
}
