package lv.javaguru.travel.insurance.rest.v1;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerJson_Test_Case_5
extends TravelCalculatePremiumControllerTestCase{
    @Override
    protected String getTestCaseFolderName() {
        return "test_case_5";
    }

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }
}
