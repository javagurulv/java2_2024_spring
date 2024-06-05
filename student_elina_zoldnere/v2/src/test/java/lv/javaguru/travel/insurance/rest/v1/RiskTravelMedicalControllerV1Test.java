package lv.javaguru.travel.insurance.rest.v1;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTestTemplate;

class RiskTravelMedicalControllerV1Test extends TravelCalculatePremiumControllerTestTemplate {

    @Override
    protected String getTestDataPath() {
        return "v1/risk_travel_medical/";
    }

    @Override
    protected String getTestCasePrefix() {
        return "RiskTMV1Test_";
    }

    @Override
    protected String getEndpoint() {
        return "/insurance/travel/api/v1/";
    }

}