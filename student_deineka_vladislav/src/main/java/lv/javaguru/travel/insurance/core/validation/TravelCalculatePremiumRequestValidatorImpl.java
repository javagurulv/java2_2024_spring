package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    @Autowired
    private List<TravelRequestValidation> travelRequestValidations;


    private List<ValidationErrors> singleErrorCollecting(TravelCalculatePremiumRequest request) {
        return travelRequestValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    private List<ValidationErrors> listErrorCollecting(TravelCalculatePremiumRequest request) {
        return travelRequestValidations.stream()
                .map(validation -> validation.listValidation(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<ValidationErrors> validation(TravelCalculatePremiumRequest premiumRequest){
        List<ValidationErrors> singleErrors = singleErrorCollecting(premiumRequest);
        List<ValidationErrors> listedErrors = listErrorCollecting(premiumRequest);
        return linkLists(singleErrors, listedErrors);
    }

    private List<ValidationErrors> linkLists(List<ValidationErrors> singleErrors, List<ValidationErrors> listedErrors) {
        return Stream.concat(singleErrors.stream(), listedErrors.stream())
                .collect(Collectors.toList());
    }



}
