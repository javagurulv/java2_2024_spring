package lv.javaguru.travel.insurance.loggers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;


public class RequestLoggerTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private RequestLogger requestLogger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void logRequest() {

    }
}