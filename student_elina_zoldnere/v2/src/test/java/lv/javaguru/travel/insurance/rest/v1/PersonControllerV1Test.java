package lv.javaguru.travel.insurance.rest.v1;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTestTemplate;

class PersonControllerV1Test extends TravelCalculatePremiumControllerTestTemplate {

    @Override
    protected String getTestDataPath() {
        return "v1/person/";
    }

    @Override
    protected String getTestCasePrefix() {
        return "PersonV1Test_";
    }

    @Override
    protected String getEndpoint() {
        return "/insurance/travel/api/v1/";
    }

}