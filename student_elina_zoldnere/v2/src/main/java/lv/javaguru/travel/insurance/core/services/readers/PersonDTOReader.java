package lv.javaguru.travel.insurance.core.services.readers;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRisksEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementPersonRisksEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PersonDTOReader {
    @Autowired
    private AgreementPersonRisksEntityRepository personRisksRepository;
    @Autowired
    private RiskDTOReader riskDTOReader;

    PersonDTO readPersonDTO(AgreementPersonEntity agreementPerson) {
        PersonEntity person = agreementPerson.getPersonEntity();

        List<AgreementPersonRisksEntity> personRisksEntities =
                personRisksRepository.findByAgreementPersonEntity(agreementPerson);

        List<RiskDTO> personRisks = personRisksEntities.stream()
                .map(personRiskEntity -> riskDTOReader.readRiskDTO(personRiskEntity))
                .toList();

        return new PersonDTO(
                person.getFirstName(),
                person.getLastName(),
                person.getPersonalCode(),
                person.getBirthDate(),
                agreementPerson.getMedicalRiskLimitLevel(),
                personRisks
        );
    }

}
