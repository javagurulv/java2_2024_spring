package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();

          validatePersonFirstName(request).ifPresent(errors::add);
          validatePersonLastName(request).ifPresent(errors::add);

          validateAgreementDateFrom(request).ifPresent(errors::add);
          validateAgreementDateTo(request).ifPresent(errors::add);
          validateAgreementDateFromNotBeforeCurrentDate(request).ifPresent(errors::add);
          validateAgreementDateToIsNotBeforeAgreementDateFrom(request).ifPresent(errors::add);
        return errors;
    }

    private Optional <ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional <ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional <ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional <ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional <ValidationError> validateAgreementDateFromNotBeforeCurrentDate(TravelCalculatePremiumRequest request){
        Date currentDate = new Date(System.currentTimeMillis());
        if (request.getAgreementDateFrom() != null) {
            return (request.getAgreementDateFrom().before(currentDate))
                    ? Optional.of(new ValidationError("agreementDateFrom", "Invalid date !"))
                    : Optional.empty();

        } else {
            return Optional.empty();
        }
    }

    private Optional <ValidationError> validateAgreementDateToIsNotBeforeAgreementDateFrom(TravelCalculatePremiumRequest request){
        if (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null) {
            return (request.getAgreementDateTo().before(request.getAgreementDateFrom()) || request.getAgreementDateTo().equals(request.getAgreementDateFrom()))
                    ? Optional.of(new ValidationError("agreementDateTo", "Invalid date !"))
                    : Optional.empty();
        }
        else {
            return Optional.empty();
        }
    }
}