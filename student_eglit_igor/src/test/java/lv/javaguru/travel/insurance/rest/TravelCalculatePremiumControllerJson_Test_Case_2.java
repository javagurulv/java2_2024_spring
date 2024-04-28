package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerJson_Test_Case_2
extends TravelCalculatePremiumControllerTestCase{
    @Override
    protected String getTestCaseFolderName() {
        return "test_case_2";
    }

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }
}
