package lv.javaguru.travel.insurance.core.services.readers.entity;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRisksEntity;
import lv.javaguru.travel.insurance.dto.serialize.RiskSerialDTO;
import org.springframework.stereotype.Component;

@Component
class RiskDTOReader {

    RiskSerialDTO readRiskDTO(AgreementPersonRisksEntity personRisksEntity) {
        return RiskSerialDTO.builder()
                .riskIc(personRisksEntity.getRiskIc())
                .premium(personRisksEntity.getPremium())
                .build();
    }

}