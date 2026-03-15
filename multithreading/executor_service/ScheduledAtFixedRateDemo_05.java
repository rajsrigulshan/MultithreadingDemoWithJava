package multithreading.executor_service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedRateDemo_05 {
    public static void main(String[] args) throws InterruptedException{
       
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
        
    



    }
}
