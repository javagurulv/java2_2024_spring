package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class ValidateCountryIsInDatabase extends RequestAgreementFieldValidationImpl {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        return (request.getCountry() != null && !request.getCountry().isBlank())
                ? validateCountry(request)
                : Optional.empty();
    }

    private Optional<ValidationError> validateCountry(TravelCalculatePremiumRequest request) {
        String countryIc = request.getCountry();
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", countryIc).isPresent()
                ? Optional.empty()
                : Optional.of(buildValidationError(countryIc));
    }

    private ValidationError buildValidationError(String countryIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_COUNTRY", countryIc);
        return validationErrorFactory.buildError("ERROR_CODE_92", List.of(placeholder));
    }

}
