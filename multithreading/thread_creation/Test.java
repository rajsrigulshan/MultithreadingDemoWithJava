package multithreading.thread_creation;

public class Test {
        public static void main(String[] args) throws InterruptedException {

            World world=new World();
            Thread t1=new Thread(world);
            t1.start();
        



            for(int i=0;i<1000;i++){
                System.out.println("Thread name.  "+Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("hello");
            }
            
        }
}
