package lv.javaguru.travel.insurance.core.services.readers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import lv.javaguru.travel.insurance.core.services.EntityReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityReaderImpl implements EntityReader {
    @Autowired
    private AgreementEntityRepository agreementRepository;
    @Autowired
    private AgreementPersonEntityRepository agreementPersonRepository;
    @Autowired
    private SelectedRisksReader selectedRisksReader;
    @Autowired
    private PersonDTOReader personReader;

    @Override
    public AgreementDTO readEntitiesByUuid(String uuid) {
        AgreementEntity agreement = agreementRepository.findByUuid(uuid).get();

        List<String> selectedRisks = selectedRisksReader.readSelectedRisks(agreement);

        List<AgreementPersonEntity> agreementPersons = agreementPersonRepository.findByAgreementEntity(agreement);

        List<PersonDTO> persons = agreementPersons.stream()
                .map(agreementPerson -> personReader.readPersonDTO(agreementPerson))
                .toList();

        return new AgreementDTO(
                agreement.getDateFrom(),
                agreement.getDateTo(),
                agreement.getCountry(),
                selectedRisks,
                persons,
                agreement.getPremium(),
                agreement.getUuid());
    }

}