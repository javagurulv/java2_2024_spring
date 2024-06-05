package lv.javaguru.travel.insurance.rest.v2;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTestTemplate;

class RiskTravelCancellationControllerV2Test extends TravelCalculatePremiumControllerTestTemplate {

    @Override
    protected String getTestDataPath() {
        return "v2/risk_travel_cancellation/";
    }

    @Override
    protected String getTestCasePrefix() {
        return "RiskTCV2Test_";
    }

    @Override
    protected String getEndpoint() {
        return "/insurance/travel/api/v2/";
    }

}