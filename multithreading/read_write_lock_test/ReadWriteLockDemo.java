package multithreading.read_write_lock_test;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWriteCounter counterObj=new ReadWriteCounter();
        Runnable writeTask=new Runnable() {
            @Override
            public void run(){
                for(int i=0;i<10;i++){
                    counterObj.increaseCounter();
                }
            }
        };

        Runnable readTask=new Runnable() {
            @Override
            public void run(){
                for(int i=0;i<10;i++){
                    counterObj.getCounter();
                }
            }
        };

        Thread t1=new Thread(writeTask,"writer_thread_1");
        Thread t2=new Thread(readTask,"reader_thread_1");
        Thread t3=new Thread(readTask,"readerThread_2");
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+": interrupted. Error: "+ e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Counter value: "+counterObj.getCounter());
    }
}
