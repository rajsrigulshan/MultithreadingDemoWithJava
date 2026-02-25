package multithreading.reentrant_lock_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private long balance=100;

    private final Lock cstmLock=new ReentrantLock();
    public boolean withdraw (int amount){
        boolean isLocked=false;
        System.out.println(Thread.currentThread().getName()+" attemting withdrawal...");
        try {
             isLocked=cstmLock.tryLock(2000,TimeUnit.MILLISECONDS);
            if(!isLocked){
                 System.out.println(Thread.currentThread().getName()+" couldn't acquire lock...");
                 return false;
            }
                if(amount<=balance){
                    try {
                        System.out.println(Thread.currentThread().getName()+" proceeding with withdrawal....");
                        Thread.sleep(1000);
                        balance-=amount;
                        System.out.println(Thread.currentThread().getName()+" Completed withdrawal...");
                        return true;
                    } catch (Exception e) {
                        System.out.println("Thread Interrupted....");
                        Thread.currentThread().interrupt();
                        return false;
                    }
                }
                else{
                    System.out.println(Thread.currentThread().getName()+" Unable to process due to insufficient balance...");
                    return  false;
                }
            
            
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName()+" Interrupted.");
            Thread.currentThread().interrupt();
            return false;
        }
        finally{
            if(isLocked) 
                cstmLock.unlock();
        }
    }
}
