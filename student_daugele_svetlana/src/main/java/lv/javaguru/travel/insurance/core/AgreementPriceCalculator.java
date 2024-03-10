package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class AgreementPriceCalculator {

    BigDecimal agreementPrice;

    BigDecimal daysWithinPeriod;

    //public AgreementPriceCalculator() {
    //???  this.agreementPrice = BigDecimal.valueOf(daysBetweenTwoDates());
    //???  this.agreementPrice = agreementPrice;


    public static long daysBetweenTwoDates() {
        long numberOfDaysWithinPeriod;
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        // Calculate the difference between dates
        long diffInMillies = Math.abs(request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime());
        numberOfDaysWithinPeriod = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return numberOfDaysWithinPeriod;
    }

    public BigDecimal getAgreementPrice() {
        daysWithinPeriod = agreementPrice;
        return agreementPrice;
    }

}
