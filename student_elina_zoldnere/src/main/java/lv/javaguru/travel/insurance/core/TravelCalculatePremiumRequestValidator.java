package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateAgreementDateChronology(request).ifPresent(errors::add);
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
        Date agreementDateFrom = request.getAgreementDateFrom();
        if (agreementDateFrom == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"));
        } else if (isDateFormatWrong(agreementDateFrom)) {
            return Optional.of(new ValidationError("agreementDateFrom",
                    "Date format must be \"yyyy-MM-dd\"!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        Date agreementDateTo = request.getAgreementDateTo();
        if (agreementDateTo == null) {
            return Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"));
        } else if (isDateFormatWrong(agreementDateTo)) {
            return Optional.of(new ValidationError("agreementDateTo",
                    "Date format must be \"yyyy-MM-dd\"!"));
        } else {
            return Optional.empty();
        }
    }

    private boolean isDateFormatWrong(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date.toString());
            return false; // Date format is correct
        } catch (ParseException e) {
            return true; // Date format is wrong
        }
    }

    private Optional<ValidationError> validateAgreementDateChronology(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        return (agreementDateFrom != null && agreementDateTo != null
                && !agreementDateFrom.before(agreementDateTo))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be before agreementDateTo!"))
                : Optional.empty();
    }

}