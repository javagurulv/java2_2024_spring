package org.javaguru.travel.insurance.jobs;

import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.services.TravelGetNotExportedAgreementUuidsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    @Value( "${agreement.xml.exporter.job.thread.count}" )
    private Integer threadCount;

    @Autowired private TravelGetNotExportedAgreementUuidsService notExportedAgreementUuidsService;
    @Autowired private AgreementXmlExporter agreementXmlExporter;


    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if (jobEnabled) {
            executeJob();
        }
    }

    private void executeJob() {
        logger.info("AgreementXmlExporterJob started");
        List<String> notExportedYetAgreementUuids = getNotExportedYetAgreementUuids();
        exportAgreements(notExportedYetAgreementUuids);
        logger.info("AgreementXmlExporterJob finished");
    }

    private List<String> getNotExportedYetAgreementUuids() {
        TravelGetNotExportedAgreementUuidsCoreResult result = notExportedAgreementUuidsService.getAgreementUuids(
                new TravelGetNotExportedAgreementUuidsCoreCommand()
        );
        return result.getAgreementUuids();
    }

    private void exportAgreements(List<String> agreementUuids) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Collection<Future<?>> futures = new LinkedList<>();
        agreementUuids.forEach(uuid -> futures.add(executor.submit(() -> agreementXmlExporter.exportAgreement(uuid))));
        waitUntilAllTasksWillBeExecuted(futures);
        executor.shutdownNow();
    }

    private static void waitUntilAllTasksWillBeExecuted(Collection<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                logger.info("AgreementXmlExporterJob exception", e);
            } catch (ExecutionException e) {
                logger.info("AgreementXmlExporterJob exception", e);
            }
        }
    }

}
