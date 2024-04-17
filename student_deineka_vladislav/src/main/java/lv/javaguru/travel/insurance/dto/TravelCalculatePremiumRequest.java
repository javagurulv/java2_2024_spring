package lv.javaguru.travel.insurance.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

}
