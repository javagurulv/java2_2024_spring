package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validator.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class TravelCalculatePremiumRequestValidator {

    @Autowired
    private PersonFirstNameIsExistAndNotEmpty personFirstNameIsExistAndNotEmpty;

    @Autowired
    private PersonLastNameIsExistAndNotEmpty personLastNameIsExistAndNotEmpty;

    @Autowired
    private DateFromIsExist dateFromIsExist;

    @Autowired
    private DateFromIsNotInPast dateFromIsNotInPast;

    @Autowired
    private DateToIsExist dateToIsExist;

    @Autowired
    private DateToIsNotInPast dateToIsNotInPast;

    @Autowired
    private TravelPeriodIsValid travelPeriodIsValid;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        personFirstNameIsExistAndNotEmpty.validatePersonFirstName(request).ifPresent(errors::add);
        personLastNameIsExistAndNotEmpty.validatePersonLastName(request).ifPresent(errors::add);
        dateFromIsExist.validateDateFrom(request).ifPresent(errors::add);
        dateToIsExist.validateDateTo(request).ifPresent(errors::add);
        dateFromIsNotInPast.validateDateFromIsNotInPast(request).ifPresent(errors::add);
        dateToIsNotInPast.validateDateToIsNotInPast(request).ifPresent(errors::add);
        travelPeriodIsValid.validateTravelPeriod(request).ifPresent(errors::add);
        return errors;
    }

}
