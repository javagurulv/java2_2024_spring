package lv.javaguru.travel.insurance.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
public class DtoV2Converter {

    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreement = buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        return coreResult.hasErrors()
                ? buildResponseWithErrors(coreResult.getErrors())
                : buildSuccessfulResponse(coreResult);
    }

    private TravelCalculatePremiumResponseV2 buildResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrorsToV2(coreErrors);
        return new TravelCalculatePremiumResponseV2(errors);
    }

    private List<ValidationError> transformValidationErrorsToV2(List<ValidationErrorDTO> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.errorCode(), error.description()))
                .toList();
    }

    private TravelCalculatePremiumResponseV2 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
        AgreementDTO agreement = coreResult.getAgreement();

        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setAgreementDateFrom(agreement.agreementDateFrom());
        response.setAgreementDateTo(agreement.agreementDateTo());
        response.setCountry(agreement.country());
        response.setAgreementPremium(agreement.agreementPremium());

        List<PersonResponseDTO> personResponseDTOs = agreement.persons().stream()
                .map(this::buildPersonForResponse)
                .toList();
        response.setPersons(personResponseDTOs);

        return response;
    }

    private PersonResponseDTO buildPersonForResponse(PersonDTO personDTO) {
        PersonResponseDTO person = new PersonResponseDTO();

        person.setPersonFirstName(personDTO.personFirstName());
        person.setPersonLastName(personDTO.personLastName());
        person.setPersonalCode(personDTO.personalCode());
        person.setPersonBirthDate(personDTO.personBirthDate());
        person.setMedicalRiskLimitLevel(personDTO.medicalRiskLimitLevel());
        person.setTravelCost(personDTO.travelCost());

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

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV2 request) {
        List<PersonDTO> persons = (request.getPersons() != null)
                ? request.getPersons().stream()
                    .map(this::buildPersonFromRequest)
                    .toList()
                : Collections.emptyList();

        return new AgreementDTO(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo(),
                request.getCountry(),
                request.getSelectedRisks(),
                persons,
                BigDecimal.ZERO,
                null);
    }

    private PersonDTO buildPersonFromRequest(PersonRequestDTO personRequestDTO) {
        return new PersonDTO(
                personRequestDTO.getPersonFirstName(),
                personRequestDTO.getPersonLastName(),
                personRequestDTO.getPersonalCode(),
                personRequestDTO.getPersonBirthDate(),
                personRequestDTO.getMedicalRiskLimitLevel(),
                personRequestDTO.getTravelCost(),
                Collections.emptyList());
    }

}
