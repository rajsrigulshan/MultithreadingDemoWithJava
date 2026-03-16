package multithreading.executor_service;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedRateDemo_05 {

    public static void main(String[] args) throws InterruptedException{

        // @raj: Next exeecution time is calculated from when the previous task started.
        // @raj: If the task takes longer than period, the next execution starts immediately.
            try (ScheduledExecutorService scheduledTask = Executors.newScheduledThreadPool(1)) {
                long startTime=System.currentTimeMillis();
                System.out.println("Task Initiated....");
                scheduledTask.scheduleAtFixedRate(()->System.out.println("hello"), 2, 3, TimeUnit.SECONDS);

                scheduledTask.schedule(scheduledTask::shutdown,20, TimeUnit.SECONDS);

                if(!scheduledTask.awaitTermination(25, TimeUnit.SECONDS)){
                    scheduledTask.shutdown();
                }
                
                System.out.println("Total Execution Time: "+(System.currentTimeMillis()-startTime));
            }
        // @raj: Runs the task after a fixed delay from the completion of the previous execution.
            try(ScheduledExecutorService scheduleAtDelay = Executors.newScheduledThreadPool(1)){
                long startTime=System.currentTimeMillis();
                System.out.println("Task schedule At Delay Initiated....");
                scheduleAtDelay.scheduleWithFixedDelay(()->System.out.println("Task Execute as fixed delay")
                ,2, 3,TimeUnit.SECONDS);


                if(!scheduleAtDelay.awaitTermination(20, TimeUnit.SECONDS)){
                    scheduleAtDelay.shutdown();
                }

                System.out.println("Total Execution Time: "+(System.currentTimeMillis()-startTime));
                
            }

    }
}
