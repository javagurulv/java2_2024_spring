package lv.javaguru.travel.insurance.core.services.writers.entity;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;

public interface EntityWriter {

    void writeEntities(AgreementDTO agreement);

}