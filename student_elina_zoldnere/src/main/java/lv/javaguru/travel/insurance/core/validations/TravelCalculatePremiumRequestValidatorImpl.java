package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    @Autowired
    List<RequestAgreementFieldValidation> agreementFieldValidation;
    @Autowired
    List<RequestPersonFieldValidation> personFieldValidation;

    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> agreementErrors = collectAgreementErrors(request);
        List<ValidationError> personErrors = collectPersonErrors(request);
        return concatenateLists(agreementErrors, personErrors);
    }

    private List<ValidationError> collectAgreementErrors (TravelCalculatePremiumRequest request) {
        List<ValidationError> singleAgreementErrors = collectSingleAgreementErrors(request);
        List<ValidationError> listAgreementErrors = collectListAgreementErrors(request);
        return concatenateLists(singleAgreementErrors, listAgreementErrors);
    }

    private List<ValidationError> collectPersonErrors (TravelCalculatePremiumRequest request) {
        List<ValidationError> singlePersonErrors = collectSinglePersonErrors(request);
        List<ValidationError> listPersonErrors = collectListPersonErrors(request);
        return concatenateLists(singlePersonErrors, listPersonErrors);
    }

    private List<ValidationError> collectSingleAgreementErrors(TravelCalculatePremiumRequest request) {
        return agreementFieldValidation.stream()
                .map(validation -> validation.validateSingle(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectListAgreementErrors(TravelCalculatePremiumRequest request) {
        return agreementFieldValidation.stream()
                .map(validation -> validation.validateList(request))
                .filter((Objects::nonNull))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectSinglePersonErrors(TravelCalculatePremiumRequest request) {
        return personFieldValidation.stream()
                .map(validation -> validation.validateSingle(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectListPersonErrors(TravelCalculatePremiumRequest request) {
        return personFieldValidation.stream()
                .map(validation -> validation.validateList(request))
                .filter((Objects::nonNull))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationError> concatenateLists(List<ValidationError> errorsListOne,
                                                   List<ValidationError> errorListTwo) {
        return Stream.concat(errorsListOne.stream(), errorListTwo.stream())
                .collect(Collectors.toList());
    }

}