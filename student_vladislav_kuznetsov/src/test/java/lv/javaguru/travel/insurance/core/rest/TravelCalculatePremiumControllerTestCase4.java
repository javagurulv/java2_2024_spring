package lv.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase4 extends TravelCalculatePremiumControllerTest{
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_4";
    }

}