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
class CountryValidation
extends TravelAgreementFieldValidationImpl{

    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (agreement.getCountry() != null && !agreement.getCountry().isBlank())
                ? validateCountry(agreement)
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateCountry(AgreementDTO agreement) {
        String countryIc = agreement.getCountry();
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", countryIc).isPresent()
                ? Optional.empty()
                : Optional.of(buildValidationError(countryIc));
    }

    private ValidationErrorDTO buildValidationError(String countryIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_COUNTRY", countryIc);
        return errorFactory.buildError("ERROR_CODE_11", List.of(placeholder));
    }
}
