package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(String personFirstName,
                                          String personLastName,
                                          LocalDate agreementDateFrom,
                                          LocalDate agreementDateTo,
                                          BigDecimal agreementPrice) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
        this.agreementPrice = agreementPrice;
    }

    public TravelCalculatePremiumResponse() {
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public LocalDate getAgreementDateFrom() {
        return agreementDateFrom;
    }

    public void setAgreementDateFrom(LocalDate agreementDateFrom) {
        this.agreementDateFrom = agreementDateFrom;
    }

    public LocalDate getAgreementDateTo() {
        return agreementDateTo;
    }

    public void setAgreementDateTo(LocalDate agreementDateTo) {
        this.agreementDateTo = agreementDateTo;
    }
    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    }
    public void setAgreementPrice(BigDecimal agreementPrice) {
        this.agreementPrice = agreementPrice;
    }

}
