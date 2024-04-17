package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired
    private TravelCalculatePremiumUnderwriting calculateUnderwriting;
    @Autowired
    private DateTimeUtil dateTimeService;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> requestErrors = requestValidator.validate(request);
        if (!requestErrors.isEmpty()) {
            return new TravelCalculatePremiumResponse(requestErrors);
        }

        return createPremiumResponse(request);
    }

    private TravelCalculatePremiumResponse createPremiumResponse(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();

        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        BigDecimal agreementPremium = calculateUnderwriting.calculateAgreementPremium(request);

        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(agreementDateFrom);
        response.setAgreementDateTo(agreementDateTo);
        response.setAgreementPremium(agreementPremium);
        response.setRiskPremiums(buildRiskPremiums(request));

        return response;
    }

    /* Seems like this is not the best design, because it duplicates operations, that are already performed in
    TravelCalculatePremiumUnderwritingImpl.
    A better design would be to get all calculation results - agreementPremium, riskIc and risk premium directly
    from TravelCalculatePremiumUnderwritingImpl.*/
    private List<RiskPremium> buildRiskPremiums(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> new RiskPremium(riskIc, BigDecimal.ZERO))
                .collect(Collectors.toList());
    }

}
