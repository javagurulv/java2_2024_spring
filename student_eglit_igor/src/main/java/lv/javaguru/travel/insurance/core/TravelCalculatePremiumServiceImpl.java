package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    @Autowired
    AgreementPriceCalculator agreementPriceCalculator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(calculateAgreementPrice(request));
        return response;
    }

    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        return agreementPriceCalculator.calculateAgreementPrice(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo());
    }
}

