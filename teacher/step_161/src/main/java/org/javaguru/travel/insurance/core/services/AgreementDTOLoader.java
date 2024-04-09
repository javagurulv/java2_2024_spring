package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import org.javaguru.travel.insurance.core.domain.entities.SelectedRiskEntity;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonRiskEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.SelectedRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class AgreementDTOLoader {

    @Autowired private AgreementEntityRepository agreementEntityRepository;
    @Autowired private SelectedRiskEntityRepository selectedRiskEntityRepository;
    @Autowired private AgreementPersonEntityRepository agreementPersonEntityRepository;
    @Autowired private AgreementPersonRiskEntityRepository agreementPersonRiskEntityRepository;

    AgreementDTO load(String uuid) {
        AgreementDTO dto = new AgreementDTO();
        AgreementEntity agreement = agreementEntityRepository.findByUuid(uuid).get();
        loadAgreementFields(dto, agreement);
        loadSelectedRisks(dto, agreement);
        loadPersons(dto, agreement);
        return dto;
    }

    private void loadPersons(AgreementDTO dto, AgreementEntity agreement) {
        List<AgreementPersonEntity> personEntities = agreementPersonEntityRepository.findByAgreement(agreement);
        List<PersonDTO> persons = personEntities.stream()
                .map(personEntity -> {
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setPersonFirstName(personEntity.getPerson().getFirstName());
                    personDTO.setPersonLastName(personEntity.getPerson().getLastName());
                    personDTO.setPersonCode(personEntity.getPerson().getPersonCode());
                    personDTO.setPersonBirthDate(personEntity.getPerson().getBirthDate());
                    personDTO.setMedicalRiskLimitLevel(personEntity.getMedicalRiskLimitLevel());

                    personDTO.setRisks(
                            agreementPersonRiskEntityRepository.findByAgreementPerson(personEntity)
                                    .stream()
                                    .map(agreementPersonRiskEntity -> {
                                        RiskDTO riskDTO = new RiskDTO();
                                        riskDTO.setRiskIc(agreementPersonRiskEntity.getRiskIc());
                                        riskDTO.setPremium(agreementPersonRiskEntity.getPremium());
                                        return riskDTO;
                                    })
                                    .collect(Collectors.toList())
                    );
                    return personDTO;
                })
                .collect(Collectors.toList());
        dto.setPersons(persons);
    }

    private void loadSelectedRisks(AgreementDTO dto, AgreementEntity agreement) {
        dto.setSelectedRisks(selectedRiskEntityRepository.findByAgreement(agreement)
                .stream().map(SelectedRiskEntity::getRiskIc)
                .collect(Collectors.toList()));
    }

    private void loadAgreementFields(AgreementDTO dto, AgreementEntity agreement) {
        dto.setUuid(agreement.getUuid());
        dto.setAgreementDateFrom(agreement.getAgreementDateFrom());
        dto.setAgreementDateTo(agreement.getAgreementDateTo());
        dto.setCountry(agreement.getCountry());
        dto.setAgreementPremium(agreement.getAgreementPremium());
    }

}
