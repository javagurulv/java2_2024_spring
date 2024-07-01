package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementUuidValidator;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
class TravelAgreementUuidValidatorImpl implements TravelAgreementUuidValidator {

    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired
    private AgreementEntityRepository agreementRepository;

    public List<ValidationErrorDTO> validate(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            return List.of(errorFactory.buildError("ERROR_CODE_21"));
        } else if (agreementRepository.findByUuid(uuid).isEmpty()) {
            Placeholder placeholder = new Placeholder("NOT_EXISTING_UUID", uuid);
            return List.of(errorFactory.buildError("ERROR_CODE_22", List.of(placeholder)));
        } else {
            return Collections.emptyList();
        }
    }

}
