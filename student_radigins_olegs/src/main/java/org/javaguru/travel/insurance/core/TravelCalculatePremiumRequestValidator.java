package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {
    @Autowired
    private DateTimeService dateTimeService;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validatePersonDateFrom(request).ifPresent(errors::add);
        validatePersonDateTo(request).ifPresent(errors::add);
        validateDateAreCorrect(request).ifPresent(errors::add);
        validateDateToInFuture(request).ifPresent(errors::add);
        validateDateFromInFuture(request).ifPresent(errors::add);
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

    private Optional<ValidationError> validatePersonDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("personDateFrom", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("personDateTo", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateAreCorrect(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null
                && (request.getAgreementDateFrom().equals(request.getAgreementDateTo()) || request.getAgreementDateFrom().after(request.getAgreementDateTo())))
                ? Optional.of(new ValidationError("agreement date to", "Must be after!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateDateFromInFuture(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentDateTime))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in the future!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateToInFuture(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (dateTo != null && dateTo.before(currentDateTime))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be in the future!"))
                : Optional.empty();
    }

}
