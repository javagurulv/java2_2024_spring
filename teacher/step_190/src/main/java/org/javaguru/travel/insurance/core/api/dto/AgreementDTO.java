package org.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class AgreementDTO {

    private String uuid;
    private Date agreementDateFrom;

    private Date agreementDateTo;

    private String country;

    private List<String> selectedRisks;

    private List<PersonDTO> persons;

    private BigDecimal agreementPremium;

}
