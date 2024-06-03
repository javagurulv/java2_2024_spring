package lv.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

class LoadTestingStatistic {

    private final List<Long> executionTimes = new ArrayList<>();

    synchronized void addExecutionTime(Long executionTime) {
        executionTimes.add(executionTime);
    }

    // Do I need `synchronized` also here?
    // These below are read-only methods, that are called when threads are closed.
    long calculateAverage() {
        OptionalDouble average = executionTimes.stream()
                .mapToLong(Long::longValue)
                .average();
        return (long) average.orElse(0);
    }

    long getMin() {
        return executionTimes.stream()
                .mapToLong(Long::longValue)
                .min()
                .orElse(0);
    }

    long getMax() {
        return executionTimes.stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);
    }

}