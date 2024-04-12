package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AgreementPriceCalculatorInterface {
    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request);
}
