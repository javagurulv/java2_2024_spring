package lv.javaguru.travel.insurance.core.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    @Scheduled(fixedRate = 5000)
    public void executeJob() {
        logger.info("SimpleJob started");
        logger.info("SimpleJob finished");
    }

}
