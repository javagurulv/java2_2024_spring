package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateAgreementDateToNotLessThanTodayTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private ValidateAgreementDateToNotLessThanToday validate;

    @Autowired
    @InjectMocks
    private ValidateHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToLessThanToday() {
        when(requestMock.getAgreementDateTo()).thenReturn(new Date(2024 - 1900, 2, 11));
        when(dateTimeService.midnightToday()).thenReturn(helper.midnightToday());

        Optional<ValidationError> result = validate.execute(requestMock);

        assertTrue(result.isPresent());
        assertEquals("agreementDateTo", result.get().getField());
        assertEquals("Must not be in past!", result.get().getMessage());
    }

}