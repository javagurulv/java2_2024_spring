package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidationAgreementDateToTest {
    private RequestValidationAgreementDateTo requestValidationAgreementDateTo;
@BeforeEach
    void setUp(){
    requestValidationAgreementDateTo = new RequestValidationAgreementDateTo();
}
@Test
    void shouldReturnErrorWhenAgreementDateToIsNull(){
    var request = new TravelCalculatePremiumRequest();
    request.setAgreementDateTo(null);
    Optional<ValidationError> error = requestValidationAgreementDateTo.executeValidation(request);

    assertTrue(error.isPresent());
    assertEquals("agreementDateTo", error.get().getField());
    assertEquals("Must not be empty!", error.get().getMessage());
}
@Test
    void shouldReturnEmptyWhenAgreementDateToIsNotNull(){
    var request = new TravelCalculatePremiumRequest();
    request.setAgreementDateTo(LocalDate.now());

    Optional<ValidationError> error = requestValidationAgreementDateTo.executeValidation(request);
    assertTrue(error.isEmpty());
    }
}