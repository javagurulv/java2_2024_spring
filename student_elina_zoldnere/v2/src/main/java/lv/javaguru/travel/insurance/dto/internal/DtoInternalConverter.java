package lv.javaguru.travel.insurance.dto.internal;

import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.serialize.AgreementSerialDTO;
import lv.javaguru.travel.insurance.dto.serialize.PersonSerialDTO;
import lv.javaguru.travel.insurance.dto.serialize.RiskSerialDTO;
import lv.javaguru.travel.insurance.dto.v2.PersonResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DtoInternalConverter {

    public TravelGetAgreementCoreCommand buildCoreCommand(String uuid) {
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
        AgreementSerialDTO agreement = coreResult.getAgreement();

        TravelGetAgreementResponse response = new TravelGetAgreementResponse();
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());

        List<PersonResponseDTO> personResponseDTOs = agreement.getPersons().stream()
                .map(this::buildPersonForResponse)
                .toList();
        response.setPersons(personResponseDTOs);
        response.setUuid(agreement.getUuid());

        return response;
    }

    private PersonResponseDTO buildPersonForResponse(PersonSerialDTO personSerial) {
        PersonResponseDTO person = new PersonResponseDTO();

        person.setPersonFirstName(personSerial.getPersonFirstName());
        person.setPersonLastName(personSerial.getPersonLastName());
        person.setPersonalCode(personSerial.getPersonalCode());
        person.setPersonBirthDate(personSerial.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(personSerial.getMedicalRiskLimitLevel());

        BigDecimal premium = personSerial.getPersonRisks().stream()
                .map(RiskSerialDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        person.setPersonPremium(premium);

        List<RiskPremium> risks = personSerial.getPersonRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .toList();
        person.setPersonRisks(risks);

        return person;
    }

}
