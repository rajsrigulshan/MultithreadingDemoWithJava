package multithreading.executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo_02 {
    public static void main(String[] args) {

        Long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            

            for (int i = 0; i < 10; i++) {
                int finalI = i;
                executor.submit(() -> {
                    System.out.println(factorial(finalI));
                });
            }

            

        }
         finally {

           // Won't accept new task but waits for the started tasks to finish &
            // At the same time won't block the main thread.
            // Necessary to terminate the ExecutorService.
            executor.shutdown();
           
            try {
                 // Returns boolean helps to block till all task executes with the help of loop.
                // Blocks the calling thread till this timeout occurs.
                // But it does not terminate executor it only controls how long the calling
                // thread waits.
                executor.awaitTermination(15, TimeUnit.SECONDS);
                System.out.println("Process Terminated");
            } catch (InterruptedException e) {
                executor.shutdown();
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            
        }
        System.out.println("Total time taken to execute:" + (System.currentTimeMillis() - startTime));
    }

    private static String factorial(int n) {
        try {
            Thread.sleep(1000);
            long value = 1;
            for (int i = 2; i <= n; i++) {
                value = value * i;
            }
            return "factorial of " + n + ": " + value;
        } catch (InterruptedException e) {
            System.out.println("Erorr: " + e.getMessage());
            Thread.currentThread().interrupt();
            return "";
        }

    }
}
