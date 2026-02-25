package multithreading.synchronized_test;

public class Test {
   public static void main(String[] args){
        Count count =new Count();
        MyClass t1=new MyClass(count);
        MyClass t2=new MyClass(count);

        t1.start();
        t2.start();

       try {
            t1.join();
            t2.join();

       } catch (InterruptedException e) {
             System.out.println("Thread Interrupted.."+"Thread Name: "+Thread.currentThread().getName()+"   "+e.getMessage());
             Thread.currentThread().interrupt();
       }
        System.out.println("Total Count: "+count.getCount());
   }
    

}
