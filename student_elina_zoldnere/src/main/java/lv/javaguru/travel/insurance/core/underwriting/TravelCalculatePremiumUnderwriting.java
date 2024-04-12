package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import java.math.BigDecimal;
import java.util.Date;

public interface TravelCalculatePremiumUnderwriting {

    BigDecimal calculateAgreementPrice(Date agreementDateFrom, Date agreementDateTo);

}
