package lv.javaguru.travel.insurance.core.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidatorInterface;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;
    @Autowired
    private TravelCalculatePremiumRequestValidatorInterface requestValidator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        CoreResponse coreResponse = new CoreResponse(requestValidator.validate(request));
        if (coreResponse.hasErrors()) {
            return new TravelCalculatePremiumResponse(coreResponse.getErrors());
        } else {
            return buildResponse(request);
        }
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPremium(premiumUnderwriting.calculateAgreementPremium(request));
        response.setSelected_risks(buildRisks(request));
        return response;
    }
    private List<RiskPremium> buildRisks(TravelCalculatePremiumRequest request){
        return request.getSelectedRisks().stream()
                .map(riskIc -> new RiskPremium(riskIc, BigDecimal.ZERO))
                .collect(Collectors.toList());
    }
}