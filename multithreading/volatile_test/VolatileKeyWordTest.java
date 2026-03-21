package multithreading.volatile_test;

class SharedObj {
    
    // boolean flag = false --> this won't work here.
    // Without volatile ,reader() might never see flag=true, due to CPU caching
    // Ensures visibility of change across threads. 
    volatile boolean flag=false;

    public void setFlagTrue() {
        flag = true;
        System.out.println("Flag set to true.");
    }

    public void printFlagIfTrue() {
        while (!flag);

        System.out.println("Flag is true now....");
    }
}

public class VolatileKeyWordTest {
    public static void main(String[] args) {
        SharedObj sharedObj = new SharedObj();

        Thread writerThread = new Thread(() -> {
        
        //Virtual threadsmake blocking operations like sleep or I/O scalable by decoupling execution from OS threads.
        Thread.startVirtualThread(()->{
               try {
            Thread.sleep(1000);
           } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
           }
        });
            sharedObj.setFlagTrue();
        });

        Thread readerThread=new Thread(sharedObj::printFlagIfTrue);


        writerThread.start();
        readerThread.start();

    }
}
