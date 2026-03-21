package multithreading.executor_service;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class FutureRunnableCallableDemo_03 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {


            try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
                
                //######################## Runnable  ###############
                // Don't Throws Exception
                // After submitting runnable task Future will have the result of asynchronous computation
                //  future.cancel(true)---> cancels the task
                // future.isCancelled()--->checks if task cancelled
                Future<?> submitRunnable = executor.submit(()-> System.out.println("Hello"),"successfully");


                //Runnable returns Void, if not explecilitly mention the result then future.get() will have null value.
                // get() of future blocks the calling thread untill the result is ready. 
                System.out.println("Task Runnable Executed: "+submitRunnable.get());

                // checks if task is finished.
                if(submitRunnable.isDone()){
                    System.out.println("Task Runnable Completed!");
                }





                //######################## Callable  ###############
                //Throws Exception
                //Here you will get the future value as String("Hello") 
                //Callable have generic return type.
                Future<String> submitCallable = executor.submit(()->"hello");

                // here also due to .get() calling thead(Main thread) will block till callable task executes.
                System.out.println("Task Callable value: "+submitCallable.get());



                //  CompletabeFuture Supports Async programming enables non-blocking, event-driven async pipelines.
                // Combines Future + functional programming(labmda chaining)
                
                //  consume result, no return 
                CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(()->10).thenApply(x->x*2).thenAccept(System.out::println);
                System.out.println(thenAccept.get());

                CompletableFuture<Integer> thenApply = CompletableFuture.supplyAsync(()->10).thenApply(x->x*2);
                // Applying get for demo....
                // Avoid get() uness necessary as it kills async benifit.
                System.out.println(thenApply.get());

                executor.shutdown();

                System.out.println("Executor Shutdown initated...");   

            }

    }
}
