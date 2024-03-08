package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import javax.xml.crypto.Data;
import java.util.Date;

class TestData {
    TravelCalculatePremiumRequest request_1(){
        return new TravelCalculatePremiumRequest("Igor",
                "Eglit",
                new Date(2024, 12, 12),
                new Date(2024, 12, 13));
    }
}
