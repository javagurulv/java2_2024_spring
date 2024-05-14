package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CountryInDatabaseValidator {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        String country = request.getCountry();
        return (!countryExistInDatabase(country))
                ? Optional.of(buildValidationError(country))
                : Optional.empty();
    }

    private ValidationError buildValidationError(String country) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_COUNTRY", country);
        return validationErrorFactory.buildError(11, List.of(placeholder));
    }

    private boolean countryExistInDatabase(String country) {
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", country).isPresent();
    }

}