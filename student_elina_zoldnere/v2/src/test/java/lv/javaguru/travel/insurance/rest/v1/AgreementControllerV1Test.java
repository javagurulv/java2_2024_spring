package lv.javaguru.travel.insurance.rest.v1;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTestTemplate;

class AgreementControllerV1Test extends TravelCalculatePremiumControllerTestTemplate {

    @Override
    protected String getTestDataPath() {
        return "v1/agreement/";
    }

    @Override
    protected String getTestCasePrefix() {
        return "AgreementV1Test_";
    }

    @Override
    protected String getEndpoint() {
        return "/insurance/travel/api/v1/";
    }

}