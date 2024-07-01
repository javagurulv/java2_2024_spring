package lv.javaguru.travel.insurance.core.services.readers.entity;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import lv.javaguru.travel.insurance.dto.serialize.AgreementSerialDTO;
import lv.javaguru.travel.insurance.dto.serialize.PersonSerialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class EntityReaderImpl implements EntityReader {
    @Autowired
    private AgreementEntityRepository agreementRepository;
    @Autowired
    private AgreementPersonEntityRepository agreementPersonRepository;
    @Autowired
    private SelectedRisksReader selectedRisksReader;
    @Autowired
    private PersonDTOReader personReader;

    @Override
    public AgreementSerialDTO readEntitiesByUuid(String uuid) {
        AgreementEntity agreement = agreementRepository.findByUuid(uuid).get();

        List<String> selectedRisks = selectedRisksReader.readSelectedRisks(agreement);

        List<AgreementPersonEntity> agreementPersons = agreementPersonRepository.findByAgreementEntity(agreement);

        List<PersonSerialDTO> persons = agreementPersons.stream()
                .map(agreementPerson -> personReader.readPersonDTO(agreementPerson))
                .toList();

        return AgreementSerialDTO.builder()
                .agreementDateFrom(agreement.getDateFrom())
                .agreementDateTo(agreement.getDateTo())
                .country(agreement.getCountry())
                .selectedRisks(selectedRisks)
                .persons(persons)
                .agreementPremium(agreement.getPremium())
                .uuid(agreement.getUuid())
                .build();
    }

}