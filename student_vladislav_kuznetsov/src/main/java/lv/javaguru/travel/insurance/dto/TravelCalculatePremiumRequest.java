package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateTo;

    public TravelCalculatePremiumRequest(String personFirstName, String personLastName) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
    }
}
