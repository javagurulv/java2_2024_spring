package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Service
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private AgreementPriceCalculator agreementPriceCalculator;
    @Autowired
    private TravelCalculatePremiumRequestValidator validate;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        CoreResponse coreResponse = new CoreResponse(validate.validate(request));
        if (coreResponse.hasErrors()) {
            return new TravelCalculatePremiumResponse(coreResponse.getErrors());
        } else {
            return getResponse(request);
        }
    }

    private TravelCalculatePremiumResponse getResponse(TravelCalculatePremiumRequest request) {
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