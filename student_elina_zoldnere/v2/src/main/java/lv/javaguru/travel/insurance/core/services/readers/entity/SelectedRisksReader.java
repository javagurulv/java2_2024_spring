package lv.javaguru.travel.insurance.core.services.readers.entity;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.SelectedRisksEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.SelectedRisksEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class SelectedRisksReader {
    @Autowired
    private SelectedRisksEntityRepository selectedRisksRepository;

    List<String> readSelectedRisks(AgreementEntity agreement) {
        List<SelectedRisksEntity> selectedRisksEntities = selectedRisksRepository.findByAgreementEntity(agreement);
        return selectedRisksEntities.stream()
                .map(SelectedRisksEntity::getRiskIc)
                .toList();
    }

}
