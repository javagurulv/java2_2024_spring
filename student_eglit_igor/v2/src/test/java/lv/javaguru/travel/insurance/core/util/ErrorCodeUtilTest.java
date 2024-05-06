package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorCodeUtilTest {

    @Test
    public void shouldReturnCorrectErrorDescription() throws IOException {
        ErrorCodeUtil errorCodeUtil = new ErrorCodeUtil();
        var result=errorCodeUtil.getErrorDescription("ERROR_CODE_1");
        assertEquals("Field personFirstName is empty!", result);
    }

    @Test
    public void shouldReturnCorrectErrorDescriptionWithPlaceholder() throws IOException{
        ErrorCodeUtil errorCodeUtil = new ErrorCodeUtil();
        var result = errorCodeUtil.getErrorDescription("ERROR_CODE_9", List.of
                (new Placeholder("NOT_EXISTING_RISK_TYPE", "Test")));
        assertEquals("Risk Type ic = Test not supported!", result);
    }

}