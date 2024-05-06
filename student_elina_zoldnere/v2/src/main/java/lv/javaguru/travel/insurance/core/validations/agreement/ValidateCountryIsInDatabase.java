package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class ValidateCountryIsInDatabase extends AgreementFieldValidationImpl {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement) {
        return (agreement.country() != null && !agreement.country().isBlank())
                ? validateCountry(agreement)
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateCountry(AgreementDTO agreement) {
        String countryIc = agreement.country();
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", countryIc).isPresent()
                ? Optional.empty()
                : Optional.of(buildValidationError(countryIc));
    }

    private ValidationErrorDTO buildValidationError(String countryIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_COUNTRY", countryIc);
        return validationErrorFactory.buildError("ERROR_CODE_92", List.of(placeholder));
    }

}
