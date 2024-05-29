package lv.javaguru.travel.insurance.rest.v2;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerBaseTest;

class PersonControllerV2Test extends TravelCalculatePremiumControllerBaseTest {

    @Override
    protected String getTestDataPath() {
        return "v2/person/";
    }

    @Override
    protected String getTestCasePrefix() {
        return "PersonV2Test_";
    }

    @Override
    protected String getEndpoint() {
        return "/insurance/travel/api/v2/";
    }

}