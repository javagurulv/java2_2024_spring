package lv.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControlerTestCase17 extends TravelCalculatePremiumControllerTest{
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_17";
    }
}
