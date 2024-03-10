package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class AgreementPriceCalculator {

    BigDecimal agreementPrice;

    BigDecimal daysBetween;

    //public AgreementPriceCalculator() {
    //???  this.agreementPrice = BigDecimal.valueOf(daysBetweenTwoDates());
    //???  this.agreementPrice = agreementPrice;


    public static long daysBetweenTwoDates() {
        long daysBetween;
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        // Calculate the difference between dates
        long diffInMillies = Math.abs(request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime());
        daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return daysBetween;
    }

    public BigDecimal getAgreementPrice() {
        daysBetween = agreementPrice;
        return agreementPrice;
    }

}
