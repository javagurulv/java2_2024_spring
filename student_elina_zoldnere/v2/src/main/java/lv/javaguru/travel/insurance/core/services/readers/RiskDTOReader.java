package lv.javaguru.travel.insurance.core.services.readers;

import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRisksEntity;
import org.springframework.stereotype.Component;

@Component
class RiskDTOReader {

    RiskDTO readRiskDTO(AgreementPersonRisksEntity personRisksEntity) {
        return new RiskDTO(
                personRisksEntity.getRiskIc(),
                personRisksEntity.getPremium()
        );
    }

}