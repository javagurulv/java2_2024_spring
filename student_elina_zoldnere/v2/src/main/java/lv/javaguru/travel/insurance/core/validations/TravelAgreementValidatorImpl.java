package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
class TravelAgreementValidatorImpl implements TravelAgreementValidator {

    @Autowired
    private TravelAgreementAllFieldValidator agreementAllFieldValidator;
    @Autowired
    private TravelPersonAllFieldValidator personAllFieldValidator;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> agreementErrors = agreementAllFieldValidator.collectAgreementErrors(agreement);
        List<ValidationErrorDTO> personErrors = personAllFieldValidator.collectPersonErrors(agreement);

        return concatenateLists(agreementErrors, personErrors);
    }

    private List<ValidationErrorDTO> concatenateLists(List<ValidationErrorDTO> errorsListOne,
                                                      List<ValidationErrorDTO> errorListTwo) {
        return Stream.concat(errorsListOne.stream(), errorListTwo.stream())
                .toList();
    }

}