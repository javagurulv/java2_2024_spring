package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class BirthDateValidator extends TravelRequestValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> validate(TravelCalculatePremiumRequest premiumRequest) {
        return (emptyPersonBirthDate(premiumRequest))
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_11"))
                : Optional.empty();
    }

    private boolean emptyPersonBirthDate(TravelCalculatePremiumRequest premiumRequest) {
        return premiumRequest.getPersonBirthDate() == null;
    }
}
