package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyCountryValidation extends TravelRequestValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> validate(TravelCalculatePremiumRequest premiumRequest) {
        return (containsTravelMedical(premiumRequest) && countryIsEmptyOrNull(premiumRequest))
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean containsTravelMedical(TravelCalculatePremiumRequest premiumRequest){
        return premiumRequest.getSelectedRisks() != null
                && premiumRequest.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean countryIsEmptyOrNull(TravelCalculatePremiumRequest premiumRequest) {
        return premiumRequest.getCountry() == null || premiumRequest.getCountry().isEmpty();
    }
}
