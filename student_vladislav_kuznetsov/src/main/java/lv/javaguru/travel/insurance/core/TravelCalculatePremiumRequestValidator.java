package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class TravelCalculatePremiumRequestValidator {
    @Autowired
    ValidatePersonFirstName validatePersonFirstName;
    @Autowired
    ValidatePersonLastName validatePersonLastName;
    @Autowired
    ValidateAgreementDateFrom validateAgreementDateFrom;
    @Autowired
    ValidateAgreementDateTo validateAgreementDateTo;
    @Autowired
    ValidateAgreementDateFromIsNotBeforeCurrentDate validateAgreementDateFromIsNotBeforeCurrentDate;
    @Autowired
    ValidateAgreementDateToIsNotBeforeAgreementDateFrom validateAgreementDateToIsNotBeforeAgreementDateFrom;
    @Autowired
    ValidateAgreementDateToIsNotBeforeCurrentDate validateAgreementDateToIsNotBeforeCurrentDate;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName.validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName.validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom.validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo.validateAgreementDateTo(request).ifPresent(errors::add);
        validateAgreementDateFromIsNotBeforeCurrentDate.validateAgreementDateFromIsNotBeforeCurrentDate(request).ifPresent(errors::add);
        validateAgreementDateToIsNotBeforeAgreementDateFrom.validateAgreementDateToIsNotBeforeAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateToIsNotBeforeCurrentDate.validateAgreementDateToIsNotBeforeCurrentDate(request).ifPresent(errors::add);
        return errors;
    }
}

//    private Optional <ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
//        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
//                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
//                : Optional.empty();
//    }
//    private Optional <ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
//        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
//                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
//                : Optional.empty();
//    }

//    private Optional <ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
//        return (request.getAgreementDateFrom() == null)
//                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
//                : Optional.empty();
//    }
//    private Optional <ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
//        return (request.getAgreementDateTo() == null)
//                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
//                : Optional.empty();
//    }

//    private Optional <ValidationError> validateAgreementDateFromIsNotBeforeCurrentDate(TravelCalculatePremiumRequest request){
//        Date currentDate = new Date(System.currentTimeMillis());
//        if (request.getAgreementDateFrom() != null) {
//            return (request.getAgreementDateFrom().before(currentDate))
//                    ? Optional.of(new ValidationError("agreementDateFrom", "Invalid date !"))
//                    : Optional.empty();
//
//        } else {
//            return Optional.empty();
//        }
//    }

//    private Optional <ValidationError> validateAgreementDateToIsNotBeforeAgreementDateFrom(TravelCalculatePremiumRequest request){
//        if (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null) {
//            return (request.getAgreementDateTo().before(request.getAgreementDateFrom()) || request.getAgreementDateTo().equals(request.getAgreementDateFrom()))
//                    ? Optional.of(new ValidationError("agreementDateTo", "Invalid date !"))
//                    : Optional.empty();
//        }
//        else {
//            return Optional.empty();
//        }
//    }
//}