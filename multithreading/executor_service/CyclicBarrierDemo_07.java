package multithreading.executor_service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo_07 {
    public static void main(String[] args) {
        int numberOfServices=3;
        try (ExecutorService executor = Executors.newFixedThreadPool(numberOfServices)) {
            CyclicBarrier barrier=new CyclicBarrier(numberOfServices,()->System.out.println("\n All Services reached at barrier.\n"));
            Future<String> submit1 = executor.submit(new DependentService_4(barrier));
            Future<String> submit2 = executor.submit(new DependentService_5(barrier));
            Future<String> submit3 = executor.submit(new DependentService_6(barrier));
            try {
                System.out.println("Waiting for services to get up......");
                System.out.println(submit1.get());
                System.out.println(submit2.get());
                System.out.println(submit3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }finally{
                executor.shutdown();
                try {
                    if(!executor.awaitTermination(10,TimeUnit.SECONDS)){
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executor.shutdownNow();
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }

    }
}




class DependentService_4 implements Callable<String>{
    private final CyclicBarrier barrier;

    public DependentService_4(CyclicBarrier barrier){
        this.barrier=barrier;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(2000);
            System.out.println("Service_1 is up and running...");
            barrier.await(4000,TimeUnit.SECONDS);
            return "successfully booted service_1";
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
            return "Failed to boot service_1";
        }
        
        
    }
    
}



class DependentService_5 implements Callable<String>{
    private final CyclicBarrier barrier;

    public DependentService_5(CyclicBarrier barrier){
        this.barrier=barrier;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(2500);
            System.out.println("Service_2 is up and running...");
            barrier.await(4000,TimeUnit.SECONDS);
            return "successfully booted service_2";
        } catch (InterruptedException |BrokenBarrierException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
            return "Failed to boot service_2";
        }
        
        
    }
    
}



class DependentService_6 implements Callable<String>{
    private final CyclicBarrier barrier;

    public DependentService_6(CyclicBarrier barrier){
        this.barrier=barrier;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(3000);
            System.out.println("Service_3 is up and running...");
            barrier.await(4000,TimeUnit.SECONDS);
            return "successfully booted service_3";
        } catch (InterruptedException |BrokenBarrierException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
            return "Failed to boot service_3";
        }
        
        
    }
    
}

