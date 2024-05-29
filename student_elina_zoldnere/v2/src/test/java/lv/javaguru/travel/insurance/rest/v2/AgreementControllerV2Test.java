package lv.javaguru.travel.insurance.rest.v2;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerBaseTest;

class AgreementControllerV2Test extends TravelCalculatePremiumControllerBaseTest {

    @Override
    protected String getTestDataPath() {
        return "v2/agreement/";
    }

    @Override
    protected String getTestCasePrefix() {
        return "AgreementV2Test_";
    }

    @Override
    protected String getEndpoint() {
        return "/insurance/travel/api/v2/";
    }

}