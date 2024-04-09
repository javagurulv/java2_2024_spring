package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementUuidValidator {

    List<ValidationErrorDTO> validate(String uuid);

}
