package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;

public interface EntityReader {
    AgreementDTO readEntitiesByUuid(String uuid);

}
