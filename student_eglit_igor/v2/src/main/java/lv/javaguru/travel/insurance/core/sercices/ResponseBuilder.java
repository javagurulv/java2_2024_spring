package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ResponseBuilder {

    TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    TravelCalculatePremiumCoreResult buildResponse(AgreementDTO agreement) {
        return new TravelCalculatePremiumCoreResult(null, agreement);
    }
}
