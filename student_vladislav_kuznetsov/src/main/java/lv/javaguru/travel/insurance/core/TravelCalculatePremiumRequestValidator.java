package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class TravelCalculatePremiumRequestValidator {
    public TravelCalculatePremiumRequestValidator() {
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
      try {
          validatePersonFirstName(request).ifPresent(errors::add);


          validatePersonLastName(request).ifPresent(errors::add);


          validateAgreementDateFrom(request).ifPresent(errors::add);


          validateAgreementDateTo(request).ifPresent(errors::add);
      }
      catch (NullPointerException nullPointerException)
      {
          System.out.println("Something went wrong");
      }
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
//        var agreementDateFromDelay = new Date(0);
        Date currentDate = new Date(System.currentTimeMillis());

//        if (request.getAgreementDateFrom() != null) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(request.getAgreementDateFrom());
//            calendar.add(Calendar.MILLISECOND, 10);
//            agreementDateFromDelay = calendar.getTime();
//        }
        return (request.getAgreementDateFrom() == null || request.getAgreementDateFrom().before(currentDate))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional <ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null || request.getAgreementDateTo().before(request.getAgreementDateFrom()) || request.getAgreementDateFrom() == null || request.getAgreementDateTo().equals(request.getAgreementDateFrom()))
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

}