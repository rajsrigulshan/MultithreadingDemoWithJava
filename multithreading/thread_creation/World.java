package multithreading.thread_creation;

public class World implements Runnable {

    @Override
    public void run() {

         for(int i=0;i<10000;i++){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(150);
                    System.out.println("Awake successfully...."+Math.ceil(Math.random()*10));
                    System.out.println("world");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                    break;
                    
                }
               
                
            }
        
    }
  
}
