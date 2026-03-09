package multithreading.executor_service;


public class ArrayOfThreadsTest_01 {

    /*
        Why ExecutorService  Important!!!
    */
    public static void main(String[] args) {

        /*
            Managing raw Thread[] scales poorly:
            No pooling: every run creates N new threads (overhead).
            No back-pressure: if N grows, you can exhaust CPU/memory.
            Hard to enforce concurrency limits.
            Exceptions inside threads are easy to miss.
        */
        long startTime=System.currentTimeMillis();
        int noOfThreads=11;
        Thread[] threads=new Thread[noOfThreads];
        for(int i=0;i<noOfThreads;i++){
            int finalI=i;
            threads[i] =new Thread(
            ()->{
                long result=factorial(finalI);
                System.out.println(result);
            }
           );
           threads[i].start();
        }
        
        try {
            /*
            
                join() waits for completion, but:
                No timeouts.
                No structured shutdown.
                If a thread deadlocks, the app may hang.
            */

            for (Thread thread : threads) {
                thread.join();
            }
            
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println("Total Execution Time: "+(System.currentTimeMillis()-startTime));

       
    }

    private static Long factorial(int n){
        try {
            Thread.sleep(1000);
            long value=1;
            for(int i=2;i<=n;i++){
                value=value*i;
            }
        return value;
        } catch (InterruptedException e) {
            System.out.println("Erorr: "+e.getMessage());
            Thread.currentThread().interrupt();
            return 0L;
        }
       
    }
}
