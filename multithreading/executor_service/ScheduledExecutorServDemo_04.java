package multithreading.executor_service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServDemo_04 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (ScheduledExecutorService schedular = Executors.newScheduledThreadPool(1)) {
            long startTime=System.currentTimeMillis();
            System.out.println("Task Initiated....");
            // Task will be executed after the fixed time once.
            ScheduledFuture<String> scheduleFuture = schedular.schedule(() ->  "Schedual task executed with delay of 2 secs"
            ,2
            ,TimeUnit.SECONDS);
            System.out.println(scheduleFuture.get());

            System.out.println("Total Execution Time: "+(System.currentTimeMillis()-startTime));

            
        }

    }

}
