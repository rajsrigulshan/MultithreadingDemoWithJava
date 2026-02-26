package multithreading.deadlock.deadlock_mimic;

public class DeadLockMimicDemo {
    public static void main(String[] args) throws InterruptedException  {
        Paper paper=new Paper();
        Pen pen= new Pen();

        Thread t1=new Thread(new TaskA(paper, pen),"Thread_1");
        Thread t2=new Thread(new TaskB(paper, pen),"Thread_2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        
        System.out.println("is completed...");
        
    }
}
