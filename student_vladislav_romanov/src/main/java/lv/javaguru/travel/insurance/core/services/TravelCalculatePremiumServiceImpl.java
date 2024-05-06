package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Autowired
    private TravelPremiumUnderwriting travelPremiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);

        return errors.isEmpty()
                ? buildResponse(request)
                : buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        LocalDate agreementDateFrom = request.getAgreementDateFrom();
        LocalDate agreementDateTo = request.getAgreementDateTo();

        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(agreementDateFrom);
        response.setAgreementDateTo(agreementDateTo);
        response.setAgreementPremium(travelPremiumUnderwriting.calculatePremium(request));
        response.setRisks(getRisksList(request));

        return response;
    }

    private List<RiskPremium> getRisksList(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(TravelCalculatePremiumServiceImpl::addRiskToList)
                .collect(Collectors.toList());
    }

    private static RiskPremium addRiskToList(String riskIc) {
        return new RiskPremium(riskIc, BigDecimal.ZERO);
    }

}
