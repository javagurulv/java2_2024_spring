package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(e -> errors.add(e));
        validatePersonLastName(request).ifPresent(e -> errors.add(e));
        validateAgreementDateFrom(request).ifPresent(e -> errors.add(e));
        validateAgreementDateTo(request).ifPresent(e -> errors.add(e));
        validateAgreementDateToIsAfterDateFrom(request).ifPresent(e -> errors.add(e));
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateToIsAfterDateFrom(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null || request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        return (!request.getAgreementDateTo().isAfter(request.getAgreementDateFrom()))
                ? Optional.of(new ValidationError("agreementDateTo", "agreementDateTo must be after agreementDateFrom!"))
                : Optional.empty();
    }
}


