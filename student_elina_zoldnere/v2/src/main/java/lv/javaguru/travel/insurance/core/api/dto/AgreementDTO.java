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

}
