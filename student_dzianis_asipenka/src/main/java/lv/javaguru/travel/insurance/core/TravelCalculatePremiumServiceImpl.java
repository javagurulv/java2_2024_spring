package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponse.setPersonFirstName(request.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(request.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementDateTo(request.getAgreementDateTo());
        travelCalculatePremiumResponse.setAgreementDateFrom(request.getAgreementDateFrom());
        travelCalculatePremiumResponse.setAgreementPrice((differenceBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())));
        return travelCalculatePremiumResponse;
    }

    private BigDecimal differenceBetweenDates(Date dataFrom, Date dataTo) {
        long diffInMillies = Math.abs(dataTo.getTime() - dataFrom.getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return new BigDecimal(BigInteger.valueOf(daysBetween));
    }
}