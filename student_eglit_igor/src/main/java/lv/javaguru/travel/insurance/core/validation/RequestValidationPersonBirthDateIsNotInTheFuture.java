package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
class RequestValidationPersonBirthDateIsNotInTheFuture extends RequestValidationIntImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        String errorCode = "ERROR_CODE_13";
        if (request.getPersonBirthDate() == null) {
            return Optional.empty();
        }
        return (request.getPersonBirthDate().isAfter(LocalDate.now()))
                ? Optional.of(validationErrorFactory.buildError(errorCode))
                : Optional.empty();
    }
}
