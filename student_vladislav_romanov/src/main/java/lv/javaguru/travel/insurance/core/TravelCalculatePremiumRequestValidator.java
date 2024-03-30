package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateDateFrom(request).ifPresent(errors::add);
        validateDateTo(request).ifPresent(errors::add);
        validateTravelPeriod(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "must exist and not to be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "must exist and not to be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null || request.getAgreementDateFrom().isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateFrom", "must exist and cannot be in past!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null || request.getAgreementDateTo().isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateTo", "must exist and cannot be in past!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateTravelPeriod(TravelCalculatePremiumRequest request) {
        DateTimeService dateTimeService = new DateTimeService();
        return (dateTimeService.calculateTravelPeriod(request.getAgreementDateFrom(), request.getAgreementDateTo()) < 1)
                ? Optional.of(new ValidationError("Travel Period", "contain incorrect data!"))
                : Optional.empty();
    }

}
