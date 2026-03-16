package multithreading.executor_service;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchDemo_06 {
    
    public static void main(String[] args) {
        int numberOfServices=3;
        try (ExecutorService services = Executors.newFixedThreadPool(numberOfServices)) {
            System.out.println("Main server started....");
            System.out.println("Waiting for other services...");
            CountDownLatch latch=new CountDownLatch(numberOfServices);
            services.submit(new DependentService_1(latch));
            services.submit(new DependentService_2(latch));
            services.submit(new DependentService_3(latch));
            try {
            // @raj: main thread will wait till latch count becomes 0. 
                latch.await();
                services.shutdown();
                System.out.println("All Services are up and running....");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    
}


class DependentService_1 implements Callable<String>{
    private final CountDownLatch latch;

    public DependentService_1(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(2000);
            System.out.println("Service_1 is up and running...");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }finally{
        // @raj: finally decreasing latch count.
            latch.countDown();
        }
        return "successful_service_1";
        
    }
    
}



class DependentService_2 implements Callable<String>{
    private final CountDownLatch latch;

    public DependentService_2(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(2500);
            System.out.println("Service_2 is up and running...");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }finally{
            latch.countDown();
        }
        return "successful_service_2";
        
    }
    
}



class DependentService_3 implements Callable<String>{
    private final CountDownLatch latch;

    public DependentService_3(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(3000);
            System.out.println("Service_3 is up and running...");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }finally{
            latch.countDown();
        }
        return "successful_service_3";
        
    }
    
}
