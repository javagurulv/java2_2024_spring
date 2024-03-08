package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Igor",
            "Eglit",
            new Date(2024, 12, 12),
            new Date(2024, 12, 13));
    TravelCalculatePremiumResponse response = service.calculatePremium(request);

    @Test
    public void ResponseFieldsEqualRequest() {
        assert (request.getPersonFirstName().equals(response.getPersonFirstName()));
        assert (request.getPersonLastName().equals(response.getPersonLastName()));
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());

    }

}