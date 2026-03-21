package multithreading.atomic_integer_test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    AtomicInteger cnt=new AtomicInteger(0);
    public  void increament(){
        cnt.incrementAndGet();
    
    }
    public int getCounter(){
        return cnt.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo atomicIntegerDemo=new AtomicIntegerDemo();

        Thread t1=new Thread(()->{
            for(int i=0;i<1000;i++){
                atomicIntegerDemo.increament();
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<1000;i++){
                atomicIntegerDemo.increament();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicIntegerDemo.getCounter());
    }

    

}
