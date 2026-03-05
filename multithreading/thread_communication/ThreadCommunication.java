package multithreading.thread_communication;

public class ThreadCommunication {
    
    public static void main(String[] args) {
        SharedResource sharedResource=new SharedResource();

        Thread producerThread=new Thread(new Producer(sharedResource));
        Thread consumerTread=new Thread(new Consumer(sharedResource));

        producerThread.start();
        consumerTread.start();
    }

}
