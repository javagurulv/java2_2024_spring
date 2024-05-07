package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record AgreementDTO(

        Date agreementDateFrom,

        Date agreementDateTo,

        String country,

        String medicalRiskLimitLevel,

        List<String> selectedRisks,

        List<PersonDTO> persons,

        BigDecimal agreementPremium) {

    // Copy constructor that allows to add agreementPremium and updated persons, effectively copying other values
    public AgreementDTO withPremium(List<PersonDTO> persons, BigDecimal agreementPremium) {
        return new AgreementDTO(agreementDateFrom,
                agreementDateTo,
                country,
                medicalRiskLimitLevel,
                selectedRisks,
                persons,
                agreementPremium);
    }

}
