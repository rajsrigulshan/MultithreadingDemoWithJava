package multithreading.thread_communication;

public class SharedResource {
    private boolean hasData;
    private int data;

    public synchronized void  produce(int value) {
        while(hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        this.data=value;
        this.hasData=true;
        System.out.println("Data produced: "+value);
        notify();


    }

    public synchronized int consume() {
        while(!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        hasData=false;
        System.out.println("Data consumed: "+data);
        notify();
        return data;
    }
}
