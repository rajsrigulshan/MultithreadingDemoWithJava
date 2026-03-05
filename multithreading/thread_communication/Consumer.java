package multithreading.thread_communication;

public class Consumer implements Runnable{
    private SharedResource sharedResource;
    public Consumer(SharedResource resource){
        this.sharedResource=resource;
    }

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            int value=sharedResource.consume();
            System.out.println("Data got after consumption"+value);
        }
    }
}
