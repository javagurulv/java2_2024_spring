package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;



@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    private DateTimeService dateTimeService = new DateTimeService();

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        System.out.println("Agreement date from is : " +request.getAgreementDateFrom());
        System.out.println("Agreement date to is : " +request.getAgreementDateTo());
        response.setAgreementPrice(BigDecimal.valueOf(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo())));
        return response;
    }
//@Override
//    public BigDecimal calculatePremiumPriceByDates(TravelCalculatePremiumRequest request) {
//        long agreementTimeFrom =request.getAgreementDateFrom().getTime();
//        long agreementTimeTo = request.getAgreementDateTo().getTime();
//        long differenceInMillis = Math.abs(agreementTimeFrom - agreementTimeTo);
//        long daysBetween = differenceInMillis / (1000 * 60 * 60 * 24);
//        return BigDecimal.valueOf(daysBetween);
//    }

}

