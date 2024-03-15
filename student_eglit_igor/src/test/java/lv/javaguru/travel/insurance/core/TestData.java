package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.time.LocalDate;

class TestData {
    TravelCalculatePremiumRequest request_1(){
        return new TravelCalculatePremiumRequest("Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13));
    }
}
