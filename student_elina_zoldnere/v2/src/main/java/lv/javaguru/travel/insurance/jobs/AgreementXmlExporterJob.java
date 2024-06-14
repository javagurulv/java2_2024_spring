package lv.javaguru.travel.insurance.jobs;

import lv.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelExportAgreementToXmlService;
import lv.javaguru.travel.insurance.core.services.TravelGetNotExportedAgreementUuidsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value("${agreement.xml.exporter.job.enabled:false}")
    private boolean jobEnabled;
    @Value("${agreement.xml.exporter.job.thread.count:1}")
    private int parallelThreadCount;

    @Autowired
    private TravelGetNotExportedAgreementUuidsService getUuidsService;
    @Autowired
    private TravelExportAgreementToXmlService service;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void executeJob() {
        if (jobEnabled) {
            logger.info("AgreementXmlExporterJob started");

            List<String> uuids = getUuids();
            ExecutorService executorService = Executors.newFixedThreadPool(parallelThreadCount);
            List<Future<Void>> futures = new ArrayList<>();

            try {
                submitTasks(uuids, executorService, futures);
                waitForTasksToCompleteExecution(futures);
            } finally {
                executorServiceShutdown(executorService);
            }

            logger.info("AgreementXmlExporterJob finished");
        }
    }

    private void submitTasks(List<String> uuids, ExecutorService executorService, List<Future<Void>> futures) {
        uuids.forEach(uuid -> futures.add(executorService.submit(() -> {
                    service.exportAgreement(new TravelExportAgreementToXmlCoreCommand(uuid));
                    return null;
                })
        ));
    }

    private void waitForTasksToCompleteExecution(List<Future<Void>> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Error executing task", e);
            }
        });
    }

    private void executorServiceShutdown(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    private List<String> getUuids() {
        TravelGetNotExportedAgreementUuidsCoreResult uuidResult =
                getUuidsService.getUuids(new TravelGetNotExportedAgreementUuidsCoreCommand());
        return uuidResult.getUuids();
    }

}
