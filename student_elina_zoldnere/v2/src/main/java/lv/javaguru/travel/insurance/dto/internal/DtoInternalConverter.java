package lv.javaguru.travel.insurance.dto.internal;

import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v2.PersonResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DtoInternalConverter {

    public TravelGetAgreementCoreCommand buildCoreCommand(TravelGetAgreementRequest request) {
        String uuid = request.getUuid();
        return new TravelGetAgreementCoreCommand(uuid);
    }

    public TravelGetAgreementResponse buildResponse(TravelGetAgreementCoreResult coreResult) {
        return coreResult.hasErrors()
                ? buildResponseWithErrors(coreResult.getErrors())
                : buildSuccessfulResponse(coreResult);
    }

    private TravelGetAgreementResponse buildResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrors(coreErrors);
        return new TravelGetAgreementResponse(errors);
    }

    private List<ValidationError> transformValidationErrors(List<ValidationErrorDTO> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.errorCode(), error.description()))
                .toList();
    }

    private TravelGetAgreementResponse buildSuccessfulResponse(TravelGetAgreementCoreResult coreResult) {
        AgreementDTO agreement = coreResult.getAgreement();

        TravelGetAgreementResponse response = new TravelGetAgreementResponse();
        response.setAgreementDateFrom(agreement.agreementDateFrom());
        response.setAgreementDateTo(agreement.agreementDateTo());
        response.setCountry(agreement.country());
        response.setAgreementPremium(agreement.agreementPremium());

        List<PersonResponseDTO> personResponseDTOs = agreement.persons().stream()
                .map(this::buildPersonForResponse)
                .toList();
        response.setPersons(personResponseDTOs);
        response.setUuid(agreement.uuid());

        return response;
    }

    private PersonResponseDTO buildPersonForResponse(PersonDTO personDTO) {
        PersonResponseDTO person = new PersonResponseDTO();

        person.setPersonFirstName(personDTO.personFirstName());
        person.setPersonLastName(personDTO.personLastName());
        person.setPersonalCode(personDTO.personalCode());
        person.setPersonBirthDate(personDTO.personBirthDate());
        person.setMedicalRiskLimitLevel(personDTO.medicalRiskLimitLevel());

        BigDecimal premium = personDTO.personRisks().stream()
                .map(RiskDTO::premium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        person.setPersonPremium(premium);

        List<RiskPremium> risks = personDTO.personRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.riskIc(), riskDTO.premium()))
                .toList();
        person.setPersonRisks(risks);

        return person;
    }

}
