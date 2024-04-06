package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationErrors> validation(TravelCalculatePremiumRequest request) {
        List<ValidationErrors> validationErrors = new ArrayList<>();
        validateFirstName(request).ifPresent(validationErrors::add);
        validateLastName(request).ifPresent(validationErrors::add);
        validateDateFrom(request).ifPresent(validationErrors::add);
        validateDateTo(request).ifPresent(validationErrors::add);

        return validationErrors;
    }

    private Optional<ValidationErrors> validateFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationErrors("firstName", "Field cannot be empty"))
                : Optional.empty();

    }

    private Optional<ValidationErrors> validateLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationErrors("lastName", "Field cannot be empty"))
                : Optional.empty();

    }

    private Optional<ValidationErrors> validateDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationErrors("dateFrom", "Field cannot be empty"))
                : Optional.empty();

    }

    private Optional<ValidationErrors> validateDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationErrors("dateTo", "Field cannot be empty"))
                : Optional.empty();

    }

}
