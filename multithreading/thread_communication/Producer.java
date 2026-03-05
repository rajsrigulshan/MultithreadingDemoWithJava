package multithreading.thread_communication;

public class Producer implements Runnable {
    private SharedResource sharedResource;

    public Producer(SharedResource resource){
        this.sharedResource=resource;
    }

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            sharedResource.produce(i);
        }
    }
}
