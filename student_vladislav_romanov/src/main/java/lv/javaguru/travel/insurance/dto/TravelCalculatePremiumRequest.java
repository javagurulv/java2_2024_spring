package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate agreementDateTo;

}
