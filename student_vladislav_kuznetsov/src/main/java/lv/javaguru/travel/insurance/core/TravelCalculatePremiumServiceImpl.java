package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(calculatePremiumPriceByDates(request));
        return response;
    }
@Override
    public BigDecimal calculatePremiumPriceByDates(TravelCalculatePremiumRequest request) {
        long agreementTimeFrom =request.getAgreementDateFrom().getTime();
        long agreementTimeTo = request.getAgreementDateTo().getTime();
        long differenceInMillis = Math.abs(agreementTimeFrom - agreementTimeTo);
        long daysBetween = differenceInMillis / (1000 * 60 * 60 * 24);
        return BigDecimal.valueOf(daysBetween);
    }


}
