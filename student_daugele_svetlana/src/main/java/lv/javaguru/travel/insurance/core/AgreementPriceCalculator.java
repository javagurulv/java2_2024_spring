package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.*;

public class AgreementPriceCalculator {

    public BigDecimal getAgreementPrice() {
        DateTimeService dateTimeService = new DateTimeService();
        BigDecimal agreementPrice = null;
        //BigDecimal agreementPrice = BigDecimal.valueOf(dateTimeService.getDaysBetweenTwoDates());
        return agreementPrice;
    }

}
