package org.javaguru.travel.insurance.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if (jobEnabled) {
            logger.info("AgreementXmlExporterJob started");
            logger.info("AgreementXmlExporterJob finished");
        }
    }

}
