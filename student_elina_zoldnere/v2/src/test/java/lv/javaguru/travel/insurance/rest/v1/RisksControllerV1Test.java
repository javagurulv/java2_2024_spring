package lv.javaguru.travel.insurance.rest.v1;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerBaseTest;

class RisksControllerV1Test extends TravelCalculatePremiumControllerBaseTest {

    @Override
    protected String getTestDataPath() {
        return "v1/risks/";
    }

    @Override
    protected String getTestCasePrefix() {
        return "RisksV1Test_";
    }

    @Override
    protected String getEndpoint() {
        return "/insurance/travel/api/v1/";
    }

}