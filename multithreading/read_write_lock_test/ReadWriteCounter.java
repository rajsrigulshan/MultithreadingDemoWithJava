package multithreading.read_write_lock_test;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
    private Long counter=0L;
    /*
     Here fairness mostly apply FIFO but Finally it depends on CPU Schedular.
    */
    ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true); 
    private final Lock writeLock=lock.writeLock();
    private final Lock readLock=lock.readLock();

    public void increaseCounter(){
    /*
        will wait till it acquires the lock. it may lead to starvation,
        can  be prevented by enabling fairness.
    */
       writeLock.lock(); 
       try{
        System.out.println(Thread.currentThread().getName()+ " increased the coounter."); 
        counter++;
        Thread.sleep(250);
       } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+": got interrupted. Error: "+e.getMessage());
            Thread.currentThread().interrupt();
       }
       finally{
        writeLock.unlock();
       }
    }


    public Long getCounter(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" reading counter");
            return counter;
        }
        finally{
            readLock.unlock();
        }
        
    }
}
