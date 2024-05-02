package lv.javaguru.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequestV2 {

    @JsonAlias("persons")
    private List<PersonRequestDTO> persons;

    private LocalDate agreementDateTo;
    @JsonAlias("selected_risks")
    private List<String> selectedRisks;
    private String country;
    private String medicalRiskLimitLevel;


}
