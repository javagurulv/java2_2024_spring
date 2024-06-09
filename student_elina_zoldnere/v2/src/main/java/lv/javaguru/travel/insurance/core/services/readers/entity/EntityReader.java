package lv.javaguru.travel.insurance.core.services.readers.entity;

import lv.javaguru.travel.insurance.dto.serialize.AgreementSerialDTO;

public interface EntityReader {
    AgreementSerialDTO readEntitiesByUuid(String uuid);

}
