package lv.javaguru.travel.insurance.dto.v1;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
public class DtoV1Converter {

    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV1 request) {
        AgreementDTO agreement = buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        return coreResult.hasErrors()
                ? buildResponseWithErrors(coreResult.getErrors())
                : buildSuccessfulResponse(coreResult);
    }

    private TravelCalculatePremiumResponseV1 buildResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrorsToV1(coreErrors);
        return new TravelCalculatePremiumResponseV1(errors);
    }

    private List<ValidationError> transformValidationErrorsToV1(List<ValidationErrorDTO> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.errorCode(), error.description()))
                .toList();
    }

    private TravelCalculatePremiumResponseV1 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
        AgreementDTO agreement = coreResult.getAgreement();
        PersonDTO person = agreement.persons().get(0);

        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        response.setPersonFirstName(person.personFirstName());
        response.setPersonLastName(person.personLastName());
        response.setPersonalCode(person.personalCode());
        response.setPersonBirthDate(person.personBirthDate());
        response.setAgreementDateFrom(agreement.agreementDateFrom());
        response.setAgreementDateTo(agreement.agreementDateTo());
        response.setCountry(agreement.country());
        response.setMedicalRiskLimitLevel(person.medicalRiskLimitLevel());
        response.setTravelCost(person.travelCost());
        response.setAgreementPremium(agreement.agreementPremium());

        List<RiskPremium> riskPremiums = person.personRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.riskIc(), riskDTO.premium()))
                .toList();
        response.setRiskPremiums(riskPremiums);

        return response;
    }

    private PersonDTO buildPerson(TravelCalculatePremiumRequestV1 request) {
        return new PersonDTO(
                request.getPersonFirstName(),
                request.getPersonLastName(),
                request.getPersonalCode(),
                request.getPersonBirthDate(),
                request.getMedicalRiskLimitLevel(),
                request.getTravelCost(),
                Collections.emptyList());
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV1 request) {
        PersonDTO person = buildPerson(request);

        return new AgreementDTO(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo(),
                request.getCountry(),
                request.getSelectedRisks(),
                List.of(person),
                BigDecimal.ZERO,
                null);
    }

}
