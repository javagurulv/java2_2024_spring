package lv.javaguru.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequestV1 {

    private String personFirstName;
    private String personLastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate personBirthDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreementDateTo;
    @JsonAlias("selected_risks")
    private List<String> selectedRisks;
    private String country;
    private String medicalRiskLimitLevel;
}
