package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

class TestData {
    TravelCalculatePremiumRequest request_1(){
        return new TravelCalculatePremiumRequest("Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13));
    }
}
