package lv.javaguru.travel.insurance.core.validation;


import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class RequestValidationCountryIsNotNullThenSelected_risksIsTravel_Medical extends RequestValidationIntImpl{

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        return (containsTravelMedical(request) && countryIsNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean containsTravelMedical(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean countryIsNullOrBlank(TravelCalculatePremiumRequest request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

}


