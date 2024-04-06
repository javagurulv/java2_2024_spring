package lv.javaguru.travel.insurance.dto;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TravelCalculatePremiumResponse extends CoreResponse{

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(List<ValidationErrors> validationErrors) {
        super(validationErrors);
    }

    //  public TravelCalculatePremiumResponse() {
//
//
  //  }
//
//

  //  public String getPersonFirstName() {
  //      return personFirstName;
  //  }
//
  //  public void setPersonFirstName(String personFirstName) {
  //      this.personFirstName = personFirstName;
  //  }
//
  //  public String getPersonLastName() {
  //      return personLastName;
  //  }
//
  //  public void setPersonLastName(String personLastName) {
  //      this.personLastName = personLastName;
  //  }
//
  //  public Date getAgreementDateFrom() {
  //      return agreementDateFrom;
  //  }
//
  //  public void setAgreementDateFrom(Date agreementDateFrom) {
  //      this.agreementDateFrom = agreementDateFrom;
  //  }
//
  //  public Date getAgreementDateTo() {
  //      return agreementDateTo;
  //  }
//
  //  public void setAgreementDateTo(Date agreementDateTo) {
  //      this.agreementDateTo = agreementDateTo;
  //  }
//
  //  public BigDecimal getAgreementPrice() {
  //      return agreementPrice;
  //  }
//
  //  public void setAgreementPrice(BigDecimal agreementPrice) {
  //      this.agreementPrice = agreementPrice;
  //  }
}
